package com.example.userdetails.route

import com.example.commondesign.navigation.Route
import kotlinx.serialization.Serializable

@Serializable
data class UserDetailScreenRoute(
    val userName: String,
): Route()