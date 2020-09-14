package com.eman.githupmvi.presentation.viewmodel

import com.eman.githupmvi.base.ViewState
import com.eman.githupmvi.models.GithubOwner
import java.lang.Exception

data class MainViewState(
    val data: List<GithubOwner>? = null,
    val isLoading: Boolean = false,
    val dataFailure: Exception? = null
) : ViewState