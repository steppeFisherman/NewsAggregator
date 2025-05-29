package com.example.newsaggregator.data.workers

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.ListenableWorker
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkerParameters
import com.example.newsaggregator.data.database.NewsDao
import com.example.newsaggregator.data.model.NewsMapperData
import com.example.newsaggregator.data.network.ApiService
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.delay
import javax.inject.Inject

@HiltWorker
class NewsWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted private val workerParameters: WorkerParameters,
    private val newsDao: NewsDao,
    val apiService: ApiService,
    val mapper: NewsMapperData
) : CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result {

        while (true) {
            try {
                val dataCloud = apiService.getFullNewsList().channel.items
                val cacheList = mapper.mapCloudToCache(dataCloud)
                newsDao.deleteAll()
                newsDao.insertNewsList(cacheList)
            } catch (_: Exception) {
            }

            delay(FIFTEEN_MINUTES_REFRESH)
        }
    }

    companion object {

        const val NAME = "NewsWorker"
        private const val FIFTEEN_MINUTES_REFRESH = 900_000L

        fun makeRequest(): OneTimeWorkRequest {
            return OneTimeWorkRequestBuilder<NewsWorker>().build()
        }
    }

    class Factory @Inject constructor(
        private val newsDao: NewsDao,
        private val apiService: ApiService,
        private val mapper: NewsMapperData
    ) : ChildWorkerFactory {

        override fun create(
            context: Context,
            workerParameters: WorkerParameters
        ): ListenableWorker {
            return NewsWorker(
                context,
                workerParameters,
                newsDao,
                apiService,
                mapper
            )
        }
    }
}