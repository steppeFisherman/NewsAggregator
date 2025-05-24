package com.example.newsaggregator.data.model.cacheModel

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "full_news_list")
data class DataCache(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val description: String,
    val dcDate: String,
    val image: String,
    val dcCreator: String,
    val link: String,
    )
