package com.example.interesting_facts_about_numbers.functionality.data.api

import com.example.interesting_facts_about_numbers.functionality.entity.Num
import com.example.interesting_facts_about_numbers.functionality.entity.NumberInterestingFact

interface ApiDataSource {
    suspend fun getFact(num: Num): NumberInterestingFact
    suspend fun getRandomFact(): NumberInterestingFact
}