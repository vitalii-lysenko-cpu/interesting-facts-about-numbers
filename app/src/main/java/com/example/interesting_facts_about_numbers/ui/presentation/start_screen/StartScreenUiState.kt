package com.example.interesting_facts_about_numbers.ui.presentation.start_screen

import com.example.interesting_facts_about_numbers.functionality.entity.NumberFact

sealed interface StartScreenUiState {
    sealed interface Initial : StartScreenUiState {
        object Loading : Initial
    }

    data class Data(
        val isValidationError: Boolean,
        val number: String,
        val history: List<NumberFact>
    ) : StartScreenUiState
}