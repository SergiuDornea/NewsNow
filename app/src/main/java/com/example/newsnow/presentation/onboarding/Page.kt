package com.example.newsnow.presentation.onboarding

import androidx.annotation.DrawableRes
import com.example.newsnow.R

data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image: Int
)

val pages = listOf(
    Page(
        title = "Lorem Ipsum",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
        image = R.drawable.onboarding1
    ),
    Page(
        title = "Lorem Ipsum",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
        image = R.drawable.onboarding2
    ),
    Page(
        title = "Lorem Ipsum",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
        image = R.drawable.onboarding3
    )
)