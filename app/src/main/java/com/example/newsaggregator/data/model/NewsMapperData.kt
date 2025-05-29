package com.example.newsaggregator.data.model

import com.example.newsaggregator.data.model.cacheModel.DataCache
import com.example.newsaggregator.data.model.cloudModel.ContentDto
import com.example.newsaggregator.data.model.cloudModel.ItemDto
import com.example.newsaggregator.domain.model.DataDomain
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone
import javax.inject.Inject

class NewsMapperData @Inject constructor() {

    private fun mapCloudToCache(dataCloud: ItemDto) = DataCache(
        id = DEFAULT_ID,
        title = dataCloud.title,
        description = cleanHtmlTags(dataCloud.description),
        dcDate = formatDate(dataCloud.dcDate),
        image = convertImageUrl(dataCloud.contents),
        dcCreator = dataCloud.dcCreator,
        link = dataCloud.link,
    )

    fun mapCloudToCache(list: List<ItemDto>): List<DataCache> =
        list.map { mapCloudToCache(it) }

    fun mapCacheToDomain(dataCache: DataCache) = DataDomain(
        id = dataCache.id,
        title = dataCache.title,
        description = dataCache.description,
        dcDate = dataCache.dcDate,
        image = dataCache.image,
        dcCreator = dataCache.dcCreator,
        link = dataCache.link,
    )

    private fun formatDate(isoDate: String?): String {
        if (isoDate.isNullOrEmpty()) return ""
        return try {
            val input = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US).apply {
                timeZone = TimeZone.getTimeZone("UTC")
            }
            val output = SimpleDateFormat("yyyy MMM dd HH:mm", Locale.US)
            val date = input.parse(isoDate) ?: return ""
            output.format(date)
        } catch (e: Exception) {
            e.toString()
        }
    }

    private fun convertImageUrl(contents: List<ContentDto>): String = try {
        contents[0].url
    } catch (e: Exception) {
        e.toString()
    }

    private fun cleanHtmlTags(html: String): String {
        return html.replace(Regex("<[^>]*>"), "")
    }

    companion object {
        private const val DEFAULT_ID = 0
    }
}