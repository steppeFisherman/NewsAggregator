package com.example.newsaggregator.ui.screens

import androidx.lifecycle.ViewModel
import com.example.newsaggregator.domain.usecases.FetchNewsListUseCase
import com.example.newsaggregator.domain.usecases.LoadDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val fetchNewsListUseCase: FetchNewsListUseCase,
    private val loadDataUseCase: LoadDataUseCase
) : ViewModel() {

    val newsList = fetchNewsListUseCase()

    init {
        loadDataUseCase()
    }

}