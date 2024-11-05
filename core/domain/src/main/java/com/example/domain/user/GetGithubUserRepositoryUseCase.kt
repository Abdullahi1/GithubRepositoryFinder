package com.example.domain.user

import com.example.domain.common.Resource
import com.example.domain.common.SuspendUseCase
import com.example.domain.model.GithubRepositoryData
import com.example.domain.model.GithubSearchUserData
import com.example.domain.model.GithubUserRepositoryData
import javax.inject.Inject

class GetGithubUserRepositoryUseCase @Inject constructor(
    private val repository: GithubUserRepository
) :
    SuspendUseCase<String, Resource<List<GithubUserRepositoryData>>>() {

    override suspend fun invoke(param: String): Resource<List<GithubUserRepositoryData>> {
        return repository.getGithubUserRepositories(param)
    }

}