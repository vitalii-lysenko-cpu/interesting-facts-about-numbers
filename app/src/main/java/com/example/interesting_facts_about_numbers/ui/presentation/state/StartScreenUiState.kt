package com.example.interesting_facts_about_numbers.ui.presentation.state

sealed interface StartScreenUiState {
    sealed interface Initial : StartScreenUiState {
        object Loading : Initial
        data class Error(val message: String) : Initial
    }

    data class Data(
        val number: String,
    ) : StartScreenUiState
}