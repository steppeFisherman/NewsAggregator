package com.example.newsaggregator.data.network

import com.example.newsaggregator.data.model.cloudModel.RssDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/{query}/rss")
    suspend fun getFullNewsList(
        @Path("query") query: String = "international"
    ): RssDto
}
