package com.example.interesting_facts_about_numbers.ui.presentation.number_fact_screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.interesting_facts_about_numbers.R.string.connection_error
import com.example.interesting_facts_about_numbers.functionality.GetNumberFactByNumberUseCase
import com.example.interesting_facts_about_numbers.functionality.GetRandomNumberFactUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NumberFactViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getRandomNumberFactUseCase: GetRandomNumberFactUseCase,
    private val getNumberFactByNumberUseCase: GetNumberFactByNumberUseCase,
) : ViewModel() {
    private val mutState: MutableStateFlow<NumberFactUiState> =
        MutableStateFlow(
            NumberFactUiState.Initial.Loading
        )

    val state: StateFlow<NumberFactUiState> = mutState

    init {
        val pasArg: String? = savedStateHandle["number"]
        viewModelScope.launch {
            mutState.emit(
                if (pasArg != "random") {
                    try {
                        NumberFactUiState.Data(
                            fact = getNumberFactByNumberUseCase.invoke(pasArg)
                        )
                    } catch (e: Exception) {
                        NumberFactUiState.Initial.Error(
                            message = connection_error.toString()
                        )
                    }
                } else {
                    try {
                        NumberFactUiState.Data(
                            fact = getRandomNumberFactUseCase.invoke()
                        )
                    } catch (e: Exception) {
                        NumberFactUiState.Initial.Error(
                            message = connection_error.toString()
                        )
                    }
                }
            )
        }
    }

    fun onClick(navController: NavHostController) {
        navController.navigate("start_screen")
    }
}