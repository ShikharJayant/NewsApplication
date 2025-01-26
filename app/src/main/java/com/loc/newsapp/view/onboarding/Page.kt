package com.loc.newsapp.view.onboarding

import androidx.annotation.DrawableRes
import com.loc.newsapp.R
import com.loc.newsapp.R.drawable.image_1

data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image: Int
)

val pages = listOf(

    Page(
        title = "Hey ",
        description = "Welcome to News Application let's get started.",
        image = image_1
    ),

    Page(
        title = "About",
        description = "1. The articles list is using Lazy column for rendering news articles.\n"+
                "2. The home screen uses Marquee effect for displaying the top headlines(2api).",


        image = R.drawable.image_2
    ),

    Page(
        title = "About",
        description = "3. Marquee effect uses 2 Api calls (/topheadlines) of (us and in).\n"+
                "4. Dark mode enabled."
        ,
        image = R.drawable.onboarding3
    )



)
