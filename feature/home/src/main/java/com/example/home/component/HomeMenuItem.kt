package com.example.home.component

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.example.home.R

data class HomeMenuItem(
    val id: Int,
    val title: String,
    @DrawableRes val icon: Int,
    val backgroundColor: Color,
    val iconContentDescription: String
){
    companion object{
        val homeMenuList = listOf(
            HomeMenuItem(
                id = 100,
                title = "Users",
                icon = R.drawable.ic_user,
                backgroundColor = Color(0xFFECF5F8),
                iconContentDescription = "Users icon"
            ),
            HomeMenuItem(
                id = 101,
                title = "Repositories",
                icon = R.drawable.ic_code_list,
                backgroundColor = Color(0xFFF6EDF8),
                iconContentDescription = "Repository icon"
            )
        )
    }
}
