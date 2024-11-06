package com.example.domain.user

import com.example.domain.common.Resource
import com.example.domain.common.SuspendUseCase
import com.example.domain.model.GithubRepositoryData
import com.example.domain.model.GithubSearchUserData
import com.example.domain.model.GithubUserProfileData
import javax.inject.Inject

class SearchGithubUserProfileUseCase @Inject constructor(
    private val repository: GithubUserRepository
) :
    SuspendUseCase<String, Resource<List<GithubUserProfileData>>>() {

    override suspend fun invoke(param: String): Resource<List<GithubUserProfileData>> {
        return repository.searchGithubUserProfile(param)
    }

}