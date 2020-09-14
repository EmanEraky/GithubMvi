package com.eman.githupmvi.data.net

import com.eman.githupmvi.data.result.MainScreenResult

class GithubInterActor {

    suspend fun getGithubList(): MainScreenResult {
        return try {
            val users = ApiServices().providesSearchEngine().getRepositories()
            MainScreenResult.DataState(data = users)
        } catch (e: Exception) {
            MainScreenResult.ErrorState(e)
        }
    }
}