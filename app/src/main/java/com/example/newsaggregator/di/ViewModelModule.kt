package com.example.newsaggregator.di

import com.example.newsaggregator.domain.usecases.FetchNewsListUseCase
import com.example.newsaggregator.domain.usecases.LoadDataUseCase
import com.example.newsaggregator.domain.usecases.NewsRepository
import com.example.newsaggregator.ui.model.NewsMapperUi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class ViewModelModule {

    @Provides
    fun provideFetchNewsListUseCase(repository: NewsRepository) =
        FetchNewsListUseCase(repository)

    @Provides
    fun provideLoadDataUseCase(repository: NewsRepository) =
        LoadDataUseCase(repository)

    @Provides
    fun provideNewsMapperUI(): NewsMapperUi = NewsMapperUi()
}
