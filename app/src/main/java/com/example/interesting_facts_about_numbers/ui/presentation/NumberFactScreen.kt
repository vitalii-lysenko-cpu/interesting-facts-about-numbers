package com.example.interesting_facts_about_numbers.ui.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.interesting_facts_about_numbers.R
import com.example.interesting_facts_about_numbers.ui.common.InterestingFactAppBarDefaults
import com.example.interesting_facts_about_numbers.ui.common.InterestingFactTopAppBar
import com.example.interesting_facts_about_numbers.ui.common.Screen
import com.example.interesting_facts_about_numbers.ui.presentation.state.NumberFactUiState
import com.example.interesting_facts_about_numbers.ui.presentation.state.NumberFactViewModel
import com.example.interesting_facts_about_numbers.ui.theme.Shapes
import com.example.interesting_facts_about_numbers.ui.theme.Teal200
import com.example.interesting_facts_about_numbers.ui.theme.backgroundTextBox
import com.example.interesting_facts_about_numbers.ui.theme.backgroundTextNumberBox

@Composable
fun NumberFactScreen(
    navController: NavHostController,
    numberFactViewModel: NumberFactViewModel = hiltViewModel(),
) {
    val state by numberFactViewModel.state.collectAsState()
    NumberFactScreen(
        state = state,
        navController = navController,
        onClick = numberFactViewModel::onClick,
    )
}

@Composable
fun NumberFactScreen(
    state: NumberFactUiState,
    navController: NavHostController,
    onClick: (NavHostController) -> Unit,
) = Screen(
    topBar = {
        InterestingFactTopAppBar(
            title = stringResource(id = R.string.number_fact),
            navigationIcon = {
                InterestingFactAppBarDefaults.UpIconButton(
                    onClick = {
                        onClick(navController)
                    })
            },
        )
    }
) {
    when (state) {
        is NumberFactUiState.Initial.Loading -> CircularProgressIndicator(
            color = Color.Blue,
        )
        is NumberFactUiState.Initial.Error ->
            DialogError(state)
        is NumberFactUiState.Data -> {
            Column(
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = backgroundTextNumberBox),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(text = state.fact.num.value)
                }
                Box(modifier = Modifier.background(color = backgroundTextBox)) {
                    Text(
                        text = state.fact.text
                    )
                }
            }
        }
    }
}


@Composable
private fun DialogError(state: NumberFactUiState.Initial.Error) {
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
            androidx.compose.material.Text(
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
internal fun NumberFactScreenPreview() {
//    NumberFactScreen(
//
//        onClick = {},
//    )
}
