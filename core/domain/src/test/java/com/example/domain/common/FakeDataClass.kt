package com.example.domain.common

import com.example.domain.model.GithubRepositoryData

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
        watchersCount = 10,
        userUrl = ""
    )
}