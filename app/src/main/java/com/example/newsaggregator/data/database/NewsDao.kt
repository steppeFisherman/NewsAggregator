package com.example.newsaggregator.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsaggregator.data.models.cacheModel.DataCache

@Dao
interface NewsDao {

    @Query("SELECT * FROM full_news_list")
    fun getNewsList(): LiveData<List<DataCache>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewsList(newsList: List<DataCache>)
}

