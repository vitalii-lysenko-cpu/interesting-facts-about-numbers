package com.example.interesting_facts_about_numbers.functionality.data.api

import com.example.interesting_facts_about_numbers.functionality.data.api.model.toEntity
import com.example.interesting_facts_about_numbers.functionality.entity.Num
import com.example.interesting_facts_about_numbers.functionality.entity.NumberInterestingFact
import javax.inject.Inject

class ApiDataSourceImpl @Inject constructor(
    private val numberApi: NumberApi
) : ApiDataSource {
    override suspend fun getFact(num: Num): NumberInterestingFact =
        numberApi.getFact(num).toEntity()

    override suspend fun getRandomFact(): NumberInterestingFact =
        numberApi.getRandomFact().toEntity()
}