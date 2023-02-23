package com.example.interesting_facts_about_numbers.functionality.data.api

import com.example.interesting_facts_about_numbers.functionality.data.api.model.toEntity
import com.example.interesting_facts_about_numbers.functionality.entity.NumberFact
import javax.inject.Inject

class ApiDataSourceImpl @Inject constructor(
    private val numberApi: NumberApi
) : ApiDataSource {
    override suspend fun getFact(num: String): NumberFact =
        numberApi.getFact(num).toEntity()

    override suspend fun getRandomFact(): NumberFact =
        numberApi.getRandomFact().toEntity()
}