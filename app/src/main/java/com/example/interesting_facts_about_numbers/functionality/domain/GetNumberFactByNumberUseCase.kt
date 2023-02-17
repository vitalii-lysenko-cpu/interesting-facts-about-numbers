package com.example.interesting_facts_about_numbers.functionality.domain

import com.example.interesting_facts_about_numbers.functionality.abstraction.FactRepository
import com.example.interesting_facts_about_numbers.functionality.entity.Num
import javax.inject.Inject

class GetNumberFactByNumberUseCase @Inject constructor(
    private val factRepository: FactRepository,
) {
    suspend operator fun invoke (num: Num) =
        factRepository.getNumberFactByNumber(num)
}