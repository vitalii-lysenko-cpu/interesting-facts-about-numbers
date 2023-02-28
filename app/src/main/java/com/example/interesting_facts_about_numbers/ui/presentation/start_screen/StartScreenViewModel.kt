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

    private val textFieldValueState: MutableStateFlow<String> =
        MutableStateFlow("")

    private val mutableHistory: MutableStateFlow<List<NumberFact>> =
        MutableStateFlow(emptyList())

    private val isErrorState: MutableStateFlow<Boolean> =
        MutableStateFlow(false)

    init {
        viewModelScope.launch {
            mutableHistory.emit(getHistoryUseCase.invoke())
        }
    }

    val startScreenState: StateFlow<StartScreenUiState> =
        combine(
            textFieldValueState,
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
            textFieldValueState.emit(value = number)
        }
    }

    fun navigateToNumberFactScreenWithNumberArgument(
        navController: NavHostController,
        number: String,
    ) {
        if (textFieldValueState.value.isNotEmpty()) {
            navigateToNumberFactScreenFromHistory(navController, number)
        } else {
            viewModelScope.launch {
                isErrorState.emit(true)
            }
        }
    }

    fun navigateToNumberFactScreenFromHistory(
        navController: NavHostController,
        number: String?,
    ) {
        navController.navigate(
            "number_fact_screen/${number}"
        )
    }


    fun navigateToNumberFactScreenWithRandomArgument(navController: NavHostController) {
        navController.navigate("number_fact_screen/random")
    }

    fun onCloseErrorDialog() {
        viewModelScope.launch {
            isErrorState.emit(false)
        }
    }
}