package com.example.newsaggregator.data.models

import com.example.newsaggregator.data.models.cacheModel.DataCache
import com.example.newsaggregator.data.models.cloudModel.RssDto
import com.example.newsaggregator.domain.model.DataDomain
import javax.inject.Inject

class NewsMapper @Inject constructor() {

    fun mapCloudToCache(dataCloud: RssDto): DataCache {
        TODO()
    }

    fun mapCacheToDomain(dataCache: DataCache): DataDomain {
        TODO()
    }
}