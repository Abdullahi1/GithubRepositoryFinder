package com.example.data.api

import com.example.data.model.repository.SearchRepositoryListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("search/repositories")
    suspend fun searchRepositories(@Query("q") query: String): SearchRepositoryListResponse

}