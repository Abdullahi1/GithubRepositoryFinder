package com.example.domain.common

import com.example.domain.model.GithubRepositoryData
import com.example.domain.model.GithubSearchUserData
import com.example.domain.model.GithubUserProfileData
import com.example.domain.model.GithubUserRepositoryData

object FakeDataClass {
    val repositoryData = GithubRepositoryData(
        repositoryId = 100,
        fullName = "Vundere/Python",
        githubUrl = "",
        topics = listOf(
            "Design system",
            "Component- misc",
            "Status- New",
            "Html",
            "Css",
            "JS",
            "Html",
            "Css",
            "JS"
        ),
        language = "Python",
        repositoryDescription = "These are random words that will be replaced in due time. Config files for my github profile",
        starGazersCount = 10,
        userUrl = "",
        userImageUrl = ""
    )

    val githubUserSearchData = GithubSearchUserData(
        userName = "Vundere",
        imageUrl = ""
    )

    val githubUserProfileData = GithubUserProfileData(
        userName = "Vundere",
        fullName = "Vundere/Python",
        imageUrl = "",
        bio = "These are random words that will be replaced in due time. ",
        location = "Lagos, Nigeria",
        emailAddress = "",
        createdAt = "",
        updateAt = "",
        followerCount = 12,
        followingCount = 13,
        htmlUrl = ""
    )

    val githubUserRepositoryData = GithubUserRepositoryData(
        repositoryId = 100,
        fullName = "Vundere/Python",
        githubUrl = "",
        topics = listOf(
            "Design system",
            "Component- misc",
            "Status- New",
            "Html",
            "Css",
            "JS",
            "Html",
            "Css",
            "JS"
        ),
        language = "Python",
        repositoryDescription = "These are random words that will be replaced in due time. Config files for my github profile",
        watchersCount = 10,
        userUrl = "",
        starGazersCount = 0,
        createdAt = "",
        updatedAt = ""
    )
}