package com.example.domain.repository

import com.example.domain.common.Resource
import com.example.domain.common.SuspendUseCase
import com.example.domain.model.GithubRepositoryData
import javax.inject.Inject

class SearchGithubRepositoryUseCase @Inject constructor(
    private val repository: GithubRepoRepository
) :
    SuspendUseCase<String, Resource<List<GithubRepositoryData>>>() {

    override suspend fun invoke(param: String): Resource<List<GithubRepositoryData>> {
        return repository.searchRepositories(param)
    }

}