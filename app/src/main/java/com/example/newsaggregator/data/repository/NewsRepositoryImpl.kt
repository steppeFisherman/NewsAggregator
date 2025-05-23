package com.example.newsaggregator.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.example.newsaggregator.data.database.NewsDao
import com.example.newsaggregator.data.model.NewsMapperData
import com.example.newsaggregator.data.workers.NewsWorker
import com.example.newsaggregator.domain.model.DataDomain
import com.example.newsaggregator.domain.usecases.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val application: Application,
    private val newsDao: NewsDao,
    private val mapper: NewsMapperData
) : NewsRepository {

    override fun fetchNewsList(): LiveData<List<DataDomain>> {
        return newsDao.getNewsList().map { list ->
            list.map {
                mapper.mapCacheToDomain(it)
            }
        }
    }

    override fun loadData() {
        val workManager = WorkManager.getInstance(application)
        workManager.enqueueUniqueWork(
            NewsWorker.NAME,
            ExistingWorkPolicy.REPLACE,
            NewsWorker.makeRequest()
        )
    }
}