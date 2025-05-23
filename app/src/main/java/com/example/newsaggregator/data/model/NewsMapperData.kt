package com.example.newsaggregator.data.model

import com.example.newsaggregator.data.model.cacheModel.DataCache
import com.example.newsaggregator.data.model.cloudModel.RssDto
import com.example.newsaggregator.domain.model.DataDomain
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import javax.inject.Inject

class NewsMapperData @Inject constructor() {

    private val calendar = Calendar.getInstance()

    fun mapCloudToCache(dataCloud: RssDto) = DataCache(
        id = DEFAULT_ID,
        lastUpdate = calendar.time.time
    )

    fun mapCacheToDomain(dataCache: DataCache) = DataDomain(
        id = dataCache.id,
        lastUpdate = convertTimestampToTime(dataCache.lastUpdate)
    )

    private fun convertTimestampToTime(timestamp: Long?): String {
        if (timestamp == null) return ""
        val stamp = Timestamp(timestamp * 1000)
        val date = Date(stamp.time)
        val pattern = "HH:mm"
        val sdf = SimpleDateFormat(pattern, Locale.getDefault())
        sdf.timeZone = TimeZone.getDefault()
        return sdf.format(date)
    }

    companion object {
        private const val DEFAULT_ID = 0
    }
}