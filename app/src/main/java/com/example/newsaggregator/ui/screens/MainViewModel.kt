package com.example.newsaggregator.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.example.newsaggregator.domain.usecases.FetchNewsListUseCase
import com.example.newsaggregator.domain.usecases.LoadDataUseCase
import com.example.newsaggregator.ui.model.NewsMapperUi
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    fetchNewsListUseCase: FetchNewsListUseCase,
    loadDataUseCase: LoadDataUseCase,
    private val mapper: NewsMapperUi
) : ViewModel() {

    val newsList = fetchNewsListUseCase().map { list ->
        list.map { dataDomain ->
            mapper.mapDomainToUi(dataDomain)
        }
    }

    init {
        loadDataUseCase()
    }
}