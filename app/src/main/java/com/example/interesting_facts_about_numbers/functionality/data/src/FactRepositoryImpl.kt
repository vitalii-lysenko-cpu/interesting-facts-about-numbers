package com.example.interesting_facts_about_numbers.functionality.data.src

import com.example.interesting_facts_about_numbers.functionality.abstraction.FactRepository
import com.example.interesting_facts_about_numbers.functionality.data.api.ApiDataSource
import com.example.interesting_facts_about_numbers.functionality.entity.Num
import com.example.interesting_facts_about_numbers.functionality.entity.NumberInterestingFact
import javax.inject.Inject

internal class FactRepositoryImpl @Inject constructor(
    private val apiDataSource: ApiDataSource
) : FactRepository {
    override suspend fun getNumberFactByNumber(num: Num): NumberInterestingFact =
        apiDataSource.getFact(num)

    override suspend fun getRandomNumberFact(): NumberInterestingFact =
        apiDataSource.getRandomFact()
}