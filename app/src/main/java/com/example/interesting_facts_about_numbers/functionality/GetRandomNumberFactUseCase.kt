package com.example.interesting_facts_about_numbers.functionality

import com.example.interesting_facts_about_numbers.functionality.abstraction.FactRepository
import javax.inject.Inject

class GetRandomNumberFactUseCase @Inject constructor(
    private val factRepository: FactRepository,
) {
    suspend operator fun invoke() =
        factRepository.getRandomNumberFact()
}