package com.example.newsaggregator.data.models.cacheModel

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "full_news_list")
data class DataCache(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
)
