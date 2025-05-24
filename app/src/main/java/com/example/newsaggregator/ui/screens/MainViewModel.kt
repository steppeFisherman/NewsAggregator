package com.example.newsaggregator.ui.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.example.newsaggregator.domain.usecases.FetchNewsListUseCase
import com.example.newsaggregator.domain.usecases.LoadDataUseCase
import com.example.newsaggregator.ui.model.DataUi
import com.example.newsaggregator.ui.model.NewsMapperUi
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    fetchNewsListUseCase: FetchNewsListUseCase,
    loadDataUseCase: LoadDataUseCase,
    private val mapper: NewsMapperUi
) : ViewModel() {

    private var _internalList = listOf<DataUi>()
    private val _list = MutableLiveData<List<DataUi>>()
    val listFiltered: LiveData<List<DataUi>>
        get() = _list

    val newsList = fetchNewsListUseCase().map { list ->
        list.map { dataDomain ->
            mapper.mapDomainToUi(dataDomain)
        }.apply {
            _internalList = this
        }
    }

    init {
        loadDataUseCase()
    }

    fun searchInList(query: String) {
        val key = query.trim()

        if (key.isEmpty()) {
            _list.value = _internalList
        } else {
            _internalList.filter { item ->
                item.title.contains(key, ignoreCase = true) ||
                        item.description.contains(key, ignoreCase = true)
            }.apply {
                _list.value = this
            }
        }
    }
}