package com.example.interesting_facts_about_numbers.ui.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.interesting_facts_about_numbers.R
import com.example.interesting_facts_about_numbers.ui.common.InterestingFactTopAppBar
import com.example.interesting_facts_about_numbers.ui.common.Screen
import com.example.interesting_facts_about_numbers.ui.presentation.start_screen.StartScreenUiState
import com.example.interesting_facts_about_numbers.ui.presentation.start_screen.StartScreenViewModel
import com.example.interesting_facts_about_numbers.ui.theme.Shapes
import com.example.interesting_facts_about_numbers.ui.theme.Teal200

@Composable
fun StartScreen(
    navController: NavHostController,
    startScreenViewModel: StartScreenViewModel = hiltViewModel(),
) {
    val state by startScreenViewModel.startScreenState.collectAsState()

    StartScreen(
        state = state,
        navController = navController,
        onChange = startScreenViewModel::onChange,
        onGetNewFact = startScreenViewModel::getNewFact,
        onGetFact = startScreenViewModel::getFact,
        onGetRandomFact = startScreenViewModel::getRandomFact,
        onCloseErrorDialog = startScreenViewModel::onCloseErrorDialog,
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun StartScreen(
    state: StartScreenUiState,
    navController: NavHostController,
    onChange: (String) -> Unit,
    onGetFact: (NavHostController, String) -> Unit,
    onGetNewFact: (NavHostController, String) -> Unit,
    onGetRandomFact: (NavHostController) -> Unit,
    onCloseErrorDialog: () -> Unit
) = Screen(
    topBar = {
        InterestingFactTopAppBar(
            title = stringResource(R.string.enter_number),
        )
    }
) {
    when (state) {
        is StartScreenUiState.Initial.Loading -> CircularProgressIndicator(color = Color.Blue)
        is StartScreenUiState.Data -> {
            SelectColumn(
                state,
                onChange,
                onGetFact,
                onGetNewFact,
                navController,
                onGetRandomFact,
                onCloseErrorDialog
            )
        }
    }
}

@Composable
@OptIn(ExperimentalComposeUiApi::class)
private fun SelectColumn(
    state: StartScreenUiState.Data,
    onChange: (String) -> Unit,
    onGetFact: (NavHostController, String) -> Unit,
    onGetNewFact: (NavHostController, String) -> Unit,
    navController: NavHostController,
    onGetRandomFact: (NavHostController) -> Unit,
    onClose: () -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val showKeyboard = remember {
        mutableStateOf(keyboardController?.show())
    }
    val focusManager = LocalFocusManager.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.number,
                onValueChange = onChange,
                label = { Text(stringResource(id = R.string.enter_number)) },
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
            )
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    onGetNewFact(navController, state.number)
                    focusManager.clearFocus()
                    showKeyboard.value = keyboardController?.hide()
                },
            ) {
                Text(text = stringResource(R.string.get_fact_by_number))

            }
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { onGetRandomFact(navController) },
            ) {
                Text(text = stringResource(R.string.get_random_fact))
            }
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items(items = state.history) { item ->
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(24.dp)
                    .clickable { onGetFact(navController, item.number) }) {
                    Text(text = item.text)
                }
            }
        }
    }

    if (!state.isValidationError) {
        DialogError(onCloseErrorDialog = onClose)
    }
}

@Composable
private fun DialogError(
    onCloseErrorDialog: () -> Unit,
) {
    Card(
        modifier = Modifier.padding(64.dp),
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
                text = stringResource(R.string.error_enter_number),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h3,
                color = Color.Red
            )
            Button(onClick = onCloseErrorDialog) {
                Text(text = stringResource(R.string.close))
            }
        }
    }
}
