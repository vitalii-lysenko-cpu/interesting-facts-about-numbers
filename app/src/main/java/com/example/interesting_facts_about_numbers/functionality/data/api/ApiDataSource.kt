package com.example.interesting_facts_about_numbers.functionality.data.api

import com.example.interesting_facts_about_numbers.functionality.entity.NumberFact

interface ApiDataSource {
    suspend fun getFact(num: String): NumberFact
    suspend fun getRandomFact(): NumberFact
}