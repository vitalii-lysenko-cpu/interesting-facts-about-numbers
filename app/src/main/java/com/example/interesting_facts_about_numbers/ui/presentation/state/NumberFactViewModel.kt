package com.example.interesting_facts_about_numbers.ui.presentation.state

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.interesting_facts_about_numbers.functionality.domain.GetNumberFactByNumberUseCase
import com.example.interesting_facts_about_numbers.functionality.domain.GetRandomNumberFactUseCase
import com.example.interesting_facts_about_numbers.functionality.entity.Num
import com.example.interesting_facts_about_numbers.functionality.entity.NumberInterestingFact
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
            NumberFactUiState.Data(
                NumberInterestingFact(
                    Num(value = ""),
                    text = ""
                )
            )
        )
    val state: StateFlow<NumberFactUiState> = mutState

    init {
        val pasArg: String = checkNotNull(savedStateHandle["number"])
        viewModelScope.launch {
            mutState.emit(
                if (pasArg != "random") {
                    try {
                        NumberFactUiState.Data(
                            fact = getNumberFactByNumberUseCase.invoke(Num(value = pasArg))
                        )
                    } catch (e: Exception) {
                        NumberFactUiState.Initial.Error("Error")
                    }
                } else {
                    try {
                        NumberFactUiState.Data(
                            fact = getRandomNumberFactUseCase.invoke()
                        )
                    } catch (e: Exception) {
                        NumberFactUiState.Initial.Error("Error")
                    }
                }
            )
        }
    }


    fun onClick(navController: NavHostController) {
        navController.navigateUp()
    }
}