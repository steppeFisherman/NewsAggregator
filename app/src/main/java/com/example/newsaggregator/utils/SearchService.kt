package com.example.newsaggregator.utils

import android.widget.Filter
import android.widget.Filterable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.newsaggregator.ui.model.DataUi

interface SearchService {

    fun result(list: List<DataUi>, newText: String)

    class Base : Filterable, SearchService {

        private var mList = mutableListOf<DataUi>()
        private var mListFiltered = mutableListOf<DataUi>()

        private var mListLivaData = MutableLiveData<List<DataUi>>()
        val mListFilteredLiveData: LiveData<List<DataUi>>
            get() = mListLivaData


        override fun getFilter(): Filter {
            TODO("Not yet implemented")
        }

        override fun result(list: List<DataUi>, newText: String) {
            TODO("Not yet implemented")
        }
    }
}