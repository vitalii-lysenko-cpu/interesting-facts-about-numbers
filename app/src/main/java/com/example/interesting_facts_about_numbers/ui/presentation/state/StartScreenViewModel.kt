package com.example.interesting_facts_about_numbers.ui.presentation.state

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StartScreenViewModel @Inject constructor(

) : ViewModel() {

    private val mutState: MutableStateFlow<StartScreenUiState> = MutableStateFlow(StartScreenUiState.Data(number = ""))
    val state: StateFlow<StartScreenUiState> = mutState

    fun onChange(number: String) {
        viewModelScope.launch {
            mutState.emit(StartScreenUiState.Data(number = number))
        }
    }

    fun getFact(navController: NavHostController, number: String) {
        navController.navigate(
            "number_fact_screen/$number"
        )
    }

    fun getRandomFact(navController: NavHostController) {
        navController.navigate(
            "number_fact_screen/random"
        )
    }
}