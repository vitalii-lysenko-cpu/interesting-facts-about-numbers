package com.example.interesting_facts_about_numbers.ui.presentation.number_fact_screen

import com.example.interesting_facts_about_numbers.functionality.entity.NumberFact

sealed interface NumberFactUiState {
    sealed interface Initial : NumberFactUiState {
        object Loading : Initial
        data class Error(val message: String) : Initial
    }

    data class Data(
        val fact: NumberFact,
    ) : NumberFactUiState
}