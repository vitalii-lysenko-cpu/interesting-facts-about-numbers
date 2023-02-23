package com.example.interesting_facts_about_numbers.ui.common

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InterestingFactTopAppBar(
    modifier: Modifier = Modifier,
    title: String? = null,
    colors: InterestingFactTopAppBarColors = InterestingFactAppBarDefaults.colors(),
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {}
) {
    CenterAlignedTopAppBar(
        title = {
            if (title != null) {
                Text(
                    text = title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        },
        modifier = modifier,
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.White,
            scrolledContainerColor = Color.White,
            navigationIconContentColor = com.example.interesting_facts_about_numbers.ui.theme.iconColor,
            titleContentColor = com.example.interesting_facts_about_numbers.ui.theme.titleColor,
            actionIconContentColor = Color.White,
        ),
        navigationIcon = {
            Box(
                modifier = Modifier
                    .padding(start = 8.dp)
            ) {
                navigationIcon()
            }
        },
        actions = {
            Row(
                modifier = Modifier
                    .padding(end = 8.dp)
            ) {
                actions()
            }
        }
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewWhitelabelTopAppBar() {
    InterestingFactTopAppBar(
        title = "Title",
        navigationIcon = { InterestingFactAppBarDefaults.UpIconButton(onClick = {}) },
    )
}

object InterestingFactAppBarDefaults {

    @Composable
    fun colors(
        backgroundColor: Color = com.example.interesting_facts_about_numbers.ui.theme.titleColor,
        iconColor: Color = com.example.interesting_facts_about_numbers.ui.theme.iconColor,
        titleColor: Color = com.example.interesting_facts_about_numbers.ui.theme.backgroundColor
    ) = InterestingFactTopAppBarColors(
        backgroundColor = backgroundColor,
        iconColor = iconColor,
        titleColor = titleColor
    )

    @Composable
    fun UpIconButton(
        onClick: () -> Unit
    ) = InterestingFactIconButton(
        icon = painterResource(
            id = com.example.interesting_facts_about_numbers.R.drawable.ic_chevron_left
        ),
        onClick = onClick
    )
}

@Immutable
data class InterestingFactTopAppBarColors(
    val backgroundColor: Color,
    val iconColor: Color,
    val titleColor: Color
)
