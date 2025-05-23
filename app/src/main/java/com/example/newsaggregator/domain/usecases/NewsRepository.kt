package com.example.newsaggregator.domain.usecases

import androidx.lifecycle.LiveData
import com.example.newsaggregator.domain.model.DataDomain

interface NewsRepository {

    fun fetchNewsList():  LiveData<List<DataDomain>>

    fun loadData()
}