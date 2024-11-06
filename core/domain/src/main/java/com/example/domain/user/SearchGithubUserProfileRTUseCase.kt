package com.example.domain.user

import com.example.domain.common.Resource
import com.example.domain.common.SuspendUseCase
import com.example.domain.model.GithubRepositoryData
import com.example.domain.model.GithubSearchUserData
import com.example.domain.model.GithubUserProfileData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchGithubUserProfileRTUseCase @Inject constructor(
    private val repository: GithubUserRepository
) :
    SuspendUseCase<String, Resource<Flow<List<GithubUserProfileData>>>>() {

    override suspend fun invoke(param: String): Resource<Flow<List<GithubUserProfileData>>> {
        return repository.searchGithubUserProfileRT(param)
    }

}