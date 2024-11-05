package com.example.domain.user

import com.example.domain.common.Resource
import com.example.domain.common.SuspendUseCase
import com.example.domain.model.GithubUserProfileData
import javax.inject.Inject

class GetGithubUserProfileUseCase @Inject constructor(
    private val repository: GithubUserRepository
) :
    SuspendUseCase<String, Resource<GithubUserProfileData>>() {

    override suspend fun invoke(param: String): Resource<GithubUserProfileData> {
        return repository.getGithubUserProfile(param)
    }

}