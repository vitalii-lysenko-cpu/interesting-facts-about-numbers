package com.example.interesting_facts_about_numbers.ui.presentation.start_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.interesting_facts_about_numbers.functionality.GetHistoryUseCase
import com.example.interesting_facts_about_numbers.functionality.entity.NumberFact
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StartScreenViewModel @Inject constructor(
    private val getHistoryUseCase: GetHistoryUseCase,
) : ViewModel() {

    private val mutableStartScreenState: MutableStateFlow<String> =
        MutableStateFlow("")

    private val mutableHistory: MutableStateFlow<List<NumberFact>> =
        MutableStateFlow(emptyList())

    private val isErrorState: MutableStateFlow<Boolean> =
        MutableStateFlow(true)

    init {
        viewModelScope.launch {
            mutableHistory.emit(getHistoryUseCase.invoke())
        }
    }

    val startScreenState: StateFlow<StartScreenUiState> =
        combine(
            mutableStartScreenState,
            mutableHistory,
            isErrorState,
        ) { number, historyList, isError ->
            StartScreenUiState.Data(
                number = number,
                history = historyList,
                isValidationError = isError,
            )
        }.stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            StartScreenUiState.Initial.Loading
        )

    fun onChange(number: String) {
        viewModelScope.launch {
            mutableStartScreenState.emit(value = number)
        }
    }

    fun getNewFact(navController: NavHostController, number: String) {
        if (mutableStartScreenState.value.isNotEmpty()) {
            getFact(navController, number)
        } else {
            viewModelScope.launch {
                isErrorState.emit(false)
            }
        }
    }

    fun getFact(navController: NavHostController, number: String) {
        navController.navigate(
            "number_fact_screen/$number"
        )
    }


    fun getRandomFact(navController: NavHostController) {
        navController.navigate("number_fact_screen/random")
    }

    fun onCloseErrorDialog() {
        viewModelScope.launch {
            isErrorState.emit(true)
        }
    }
}