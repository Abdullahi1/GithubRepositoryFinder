package com.example.data.common

import com.example.data.model.repository.SearchRepositoryListResponse
import com.example.data.model.user.GithubUserProfileResponse
import com.example.data.model.user.GithubUserRepositoryResponse
import com.example.data.model.user.SearchUserListResponse

object FakeResponseData {
    val fakeGithubRepoSearchResponse = SearchRepositoryListResponse(100, false)
    val fakeGithubUserSearchResponse = SearchUserListResponse(100, false)
    val fakeGithubUserProfileResponse = GithubUserProfileResponse()
    val fakeGithubUserRepositoryResponse = GithubUserRepositoryResponse()
}