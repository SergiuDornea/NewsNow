package com.example.newsnow.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.example.newsnow.presentation.Dimens.INDICATOR_SIZE
import com.example.newsnow.ui.theme.BlueGray

@Composable
fun PageIndicator(
    modifier: Modifier = Modifier,
    pagesSize: Int,
    selectedPage: Int,
    selectedPageColor: Color = MaterialTheme.colorScheme.primary,
    unselectedPageColor: Color = BlueGray,
) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.SpaceBetween) {
        repeat(times = pagesSize) { page ->
            Box(
                modifier = Modifier
                    .size(INDICATOR_SIZE)
                    .clip(CircleShape)
                    .background(color = if (page == selectedPage) selectedPageColor else unselectedPageColor)
            )
        }
    }

}