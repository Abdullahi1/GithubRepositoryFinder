package com.example.userdetails.route

import kotlinx.serialization.Serializable

@Serializable
data class UserDetailScreenRoute(
    val userName: String,
)