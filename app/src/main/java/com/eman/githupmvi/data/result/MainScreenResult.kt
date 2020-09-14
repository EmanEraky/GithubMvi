package com.eman.githupmvi.data.result

import com.eman.githupmvi.base.Result
import com.eman.githupmvi.models.GithubOwner
import java.lang.Exception

sealed class MainScreenResult : Result {
    object LoadingState : MainScreenResult()
    data class DataState(val data: List<GithubOwner>) : MainScreenResult()
    data class ErrorState(val error: Exception) : MainScreenResult()
}