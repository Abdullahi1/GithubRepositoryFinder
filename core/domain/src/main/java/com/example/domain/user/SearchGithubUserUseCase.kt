package com.example.domain.user

import com.example.domain.common.Resource
import com.example.domain.common.SuspendUseCase
import com.example.domain.model.GithubRepositoryData
import com.example.domain.model.GithubSearchUserData
import javax.inject.Inject

class SearchGithubUserUseCase @Inject constructor(
    private val repository: GithubUserRepository
) :
    SuspendUseCase<String, Resource<List<GithubSearchUserData>>>() {

    override suspend fun invoke(param: String): Resource<List<GithubSearchUserData>> {
        return repository.searchGithubUser(param)
    }

}