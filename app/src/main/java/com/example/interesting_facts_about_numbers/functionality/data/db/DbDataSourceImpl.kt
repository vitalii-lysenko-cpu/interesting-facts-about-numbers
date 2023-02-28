package com.example.interesting_facts_about_numbers.functionality.data.db

import com.example.interesting_facts_about_numbers.functionality.entity.NumberFact
import javax.inject.Inject

class DbDataSourceImpl @Inject constructor(
    private val factDao: FactDao
) : DbDataSource {
    override suspend fun getAllNumbers(): List<NumberFact> =
        factDao.getAllNumbers()

    override suspend fun getNumberFactByNumber(number: String?): NumberFact =
        factDao.getNumberFactByNumber(number)

    override suspend fun insert(numberFact: NumberFact) {
        factDao.insert(numberFact)
    }

    override suspend fun deleteNumber(numberFact: NumberFact) {
        factDao.deleteNumber(numberFact)
    }
}