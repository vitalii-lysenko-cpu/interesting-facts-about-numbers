package com.example.interesting_facts_about_numbers.ui.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.interesting_facts_about_numbers.R
import com.example.interesting_facts_about_numbers.ui.common.InterestingFactTopAppBar
import com.example.interesting_facts_about_numbers.ui.common.Screen
import com.example.interesting_facts_about_numbers.ui.presentation.state.StartScreenUiState
import com.example.interesting_facts_about_numbers.ui.presentation.state.StartScreenViewModel
import com.example.interesting_facts_about_numbers.ui.theme.Shapes
import com.example.interesting_facts_about_numbers.ui.theme.Teal200

@Composable
fun StartScreen(
    navController: NavHostController,
    startScreenViewModel: StartScreenViewModel = hiltViewModel(),
) {
    val state by startScreenViewModel.state.collectAsState()
    StartScreen(
        state = state,
        navController = navController,
        onChange = startScreenViewModel::onChange,
        onGetFact = startScreenViewModel::getFact,
        onGetRandomFact = startScreenViewModel::getRandomFact,
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun StartScreen(
    state: StartScreenUiState,
    navController: NavHostController,
    onChange: (String) -> Unit,
    onGetFact: (NavHostController,String) -> Unit,
    onGetRandomFact: (NavHostController) -> Unit,
) = Screen(
    topBar = {
        InterestingFactTopAppBar(
            title = stringResource(R.string.enter_number),
        )
    }
) {
    when (state) {
        is StartScreenUiState.Initial.Loading -> CircularProgressIndicator(
            color = Color.Blue,
        )
        is StartScreenUiState.Initial.Error ->
            DialogError(state)
        is StartScreenUiState.Data -> {
            SelectColumn(state, onChange, onGetFact, navController, onGetRandomFact)
        }
    }
}

@Composable
@OptIn(ExperimentalComposeUiApi::class)
private fun SelectColumn(
    state: StartScreenUiState.Data,
    onChange: (String) -> Unit,
    onGetFact: (NavHostController, String) -> Unit,
    navController: NavHostController,
    onGetRandomFact: (NavHostController) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val showKeyboard = remember {
        mutableStateOf(keyboardController?.show())
    }
    val focusManager = LocalFocusManager.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.number,
            onValueChange = onChange,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Number
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    onGetFact(navController, state.number)
                    focusManager.clearFocus()
                    showKeyboard.value = keyboardController?.hide()
                }
            ),
            label = { stringResource(id = R.string.enter_number) },
        )
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                onGetFact(navController,state.number)
                focusManager.clearFocus()
                showKeyboard.value = keyboardController?.hide()
            },
        ) {
            Text(text = "Get fact by number")

        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {onGetRandomFact(navController)},
        ) {
            Text(text = "Get fact by number")
        }
    }
}

@Composable
private fun DialogError(state: StartScreenUiState.Initial.Error) {
    Card(
        shape = Shapes.medium,
        backgroundColor = Teal200,
        elevation = 2.dp
    ) {
        Column(
            modifier = Modifier
                .padding(
                    start = 24.dp,
                    top = 32.dp,
                    end = 24.dp,
                    bottom = 40.dp
                )
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = state.message,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h3,
                color = Color.Red
            )
        }
    }
}

@Preview
@Composable
internal fun AppPreview() {
//    StartScreen(
//        onChange = {},
//        onGetFact = {},
//        onGetRandomFact = {},
//    )
}