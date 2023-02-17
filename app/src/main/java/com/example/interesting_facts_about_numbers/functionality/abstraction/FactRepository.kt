package com.example.interesting_facts_about_numbers.functionality.abstraction

import com.example.interesting_facts_about_numbers.functionality.entity.Num
import com.example.interesting_facts_about_numbers.functionality.entity.NumberInterestingFact

interface FactRepository {

    suspend fun getNumberFactByNumber(num: Num): NumberInterestingFact

    suspend fun getRandomNumberFact(): NumberInterestingFact
}