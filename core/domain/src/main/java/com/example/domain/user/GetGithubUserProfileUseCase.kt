package com.example.domain.user

import com.example.domain.common.Resource
import com.example.domain.common.SuspendUseCase
import com.example.domain.model.GithubRepositoryData
import com.example.domain.model.GithubSearchUserData
import com.example.domain.model.GithubUserData
import javax.inject.Inject

class GetGithubUserProfileUseCase @Inject constructor(
    private val repository: GithubUserRepository
) :
    SuspendUseCase<String, Resource<GithubUserData>>() {

    override suspend fun invoke(param: String): Resource<GithubUserData> {
        return repository.getGithubUserProfile(param)
    }

}