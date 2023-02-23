package com.example.interesting_facts_about_numbers.functionality.data.db

import com.example.interesting_facts_about_numbers.functionality.entity.NumberFact

interface DbDataSource {

    suspend fun getAllNumbers(): List<NumberFact>

    suspend fun getNumberFactByNumber(number: String): NumberFact

    suspend fun insert(numberFact: NumberFact)

    suspend fun deleteNumber(numberFact: NumberFact)
}