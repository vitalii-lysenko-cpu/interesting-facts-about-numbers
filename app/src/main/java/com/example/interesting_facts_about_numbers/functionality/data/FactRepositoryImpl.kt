package com.example.interesting_facts_about_numbers.functionality.data

import com.example.interesting_facts_about_numbers.functionality.abstraction.FactRepository
import com.example.interesting_facts_about_numbers.functionality.data.api.ApiDataSource
import com.example.interesting_facts_about_numbers.functionality.data.db.DbDataSource
import com.example.interesting_facts_about_numbers.functionality.entity.NumberFact
import javax.inject.Inject

internal class FactRepositoryImpl @Inject constructor(
    private val apiDataSource: ApiDataSource,
    private val dbDataSource: DbDataSource,
) : FactRepository {
    override suspend fun getNumberFactByNumber(num: String?): NumberFact {
        val fact = apiDataSource.getFact(num)
        dbDataSource.insert(fact)
        return dbDataSource.getNumberFactByNumber(num)
    }

    override suspend fun getRandomNumberFact(): NumberFact {
        val fact = apiDataSource.getRandomFact()
        dbDataSource.insert(fact)
        return dbDataSource.getNumberFactByNumber(fact.number)
    }

    override suspend fun getHistory(): List<NumberFact> =
        dbDataSource.getAllNumbers()
}