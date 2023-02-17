package com.example.interesting_facts_about_numbers.ui.common

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter

@Composable
fun InterestingFactIconButton(
    icon: Painter,
    modifier: Modifier = Modifier,
    tint: Color = LocalContentColor.current,
    contentDescription: String? = null,
    enabled: Boolean = true,
    onClick: () -> Unit
) = IconButton(
    onClick = onClick,
    modifier = if (enabled) {
        modifier
    } else {
        modifier.alpha(0.4F)
    }
) {
    Icon(
        painter = icon,
        contentDescription = contentDescription,
        tint = tint
    )
}
