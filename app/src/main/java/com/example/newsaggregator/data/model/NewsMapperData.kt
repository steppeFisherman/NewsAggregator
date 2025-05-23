package com.example.newsaggregator.data.model

import com.example.newsaggregator.data.model.cacheModel.DataCache
import com.example.newsaggregator.data.model.cloudModel.ContentDto
import com.example.newsaggregator.data.model.cloudModel.ItemDto
import com.example.newsaggregator.domain.model.DataDomain
import java.util.Calendar
import javax.inject.Inject

class NewsMapperData @Inject constructor() {

    private val calendar = Calendar.getInstance()

    private fun mapCloudToCache(dataCloud: ItemDto) = DataCache(
        id = DEFAULT_ID,
        title = dataCloud.title,
        description = dataCloud.description,
        pubDate = dataCloud.description,
        image = convertImageUrl(dataCloud.contents),
        dcCreator = dataCloud.dcCreator
    )

    fun mapCloudToCache(list: List<ItemDto>): List<DataCache> =
        list.map { mapCloudToCache(it) }

    fun mapCacheToDomain(dataCache: DataCache) = DataDomain(
        id = dataCache.id,
        title = dataCache.title,
        description = dataCache.description,
        pubDate = dataCache.pubDate,
        image = dataCache.image,
        dcCreator = dataCache.dcCreator,
    )

    private fun convertImageUrl(contents: List<ContentDto>): String = try {
        contents[0].url
    } catch (e: Exception) {
        ""
    }

//    private fun convertTimestampToTime(timestamp: Long?): String {
//        if (timestamp == null) return ""
//        val stamp = Timestamp(timestamp * 1000)
//        val date = Date(stamp.time)
//        val pattern = "HH:mm"
//        val sdf = SimpleDateFormat(pattern, Locale.getDefault())
//        sdf.timeZone = TimeZone.getDefault()
//        return sdf.format(date)
//    }

    companion object {
        private const val DEFAULT_ID = 0
    }
}