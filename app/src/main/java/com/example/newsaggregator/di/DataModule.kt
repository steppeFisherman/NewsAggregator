package com.example.newsaggregator.di

import android.app.Application
import android.content.Context
import com.example.newsaggregator.data.database.AppDatabase
import com.example.newsaggregator.data.database.NewsDao
import com.example.newsaggregator.data.models.NewsMapper
import com.example.newsaggregator.data.network.ApiFactory
import com.example.newsaggregator.data.network.ApiService
import com.example.newsaggregator.data.repository.NewsRepositoryImpl
import com.example.newsaggregator.data.workers.ChildWorkerFactory
import com.example.newsaggregator.data.workers.NewsWorker
import com.example.newsaggregator.domain.usecases.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun providesNewsRepository(
        application: Application,
        newsDao: NewsDao,
        mapper: NewsMapper
    ): NewsRepository =
        NewsRepositoryImpl(
            application = application,
            newsDao = newsDao,
            mapper = mapper
        )

    @Provides
    @IntoMap
    @WorkerKey(NewsWorker::class)
    fun provideWorkerFactory(factory: NewsWorker.Factory): ChildWorkerFactory {
        return factory
    }

    @Provides
    @Singleton
    fun provideApiService(): ApiService = ApiFactory.apiService

    @Provides
    @Singleton
    fun provideNewsDao(
        @ApplicationContext context: Context
    ): NewsDao = AppDatabase.getInstance(context).newsDao()

    @Provides
    @Singleton
    fun provideNewsMapper(): NewsMapper = NewsMapper()
}
