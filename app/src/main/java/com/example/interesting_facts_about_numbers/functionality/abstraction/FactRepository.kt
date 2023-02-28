package com.example.interesting_facts_about_numbers.functionality.abstraction

import com.example.interesting_facts_about_numbers.functionality.entity.NumberFact

interface FactRepository {
    suspend fun getNumberFactByNumber(num: String?): NumberFact

    suspend fun getRandomNumberFact(): NumberFact

    suspend fun getHistory(): List<NumberFact>
}