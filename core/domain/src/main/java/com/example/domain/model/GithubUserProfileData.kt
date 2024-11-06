package com.example.domain.model

data class GithubUserProfileData(
    val userName: String,
    val fullName: String,
    val bio: String,
    val location: String,
    val emailAddress: String,
    val imageUrl: String,
    val followerCount: Int,
    val followingCount: Int,
    val htmlUrl: String,
    val createdAt: String,
    val updateAt: String
)
