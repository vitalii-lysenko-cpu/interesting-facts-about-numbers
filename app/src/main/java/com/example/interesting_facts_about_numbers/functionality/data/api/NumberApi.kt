package com.example.interesting_facts_about_numbers.functionality.data.api

import com.example.interesting_facts_about_numbers.functionality.data.api.model.ApiNumberInterestingFact
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface NumberApi {

    @Headers("Content-type: application/json")
    @GET("{number}")
    suspend fun getFact(
        @Path("number") num: String?
    ): ApiNumberInterestingFact

    @Headers("Content-type: application/json")
    @GET("random")
    suspend fun getRandomFact(): ApiNumberInterestingFact
}