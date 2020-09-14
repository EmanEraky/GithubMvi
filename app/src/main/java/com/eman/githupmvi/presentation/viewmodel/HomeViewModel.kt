package com.eman.githupmvi.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.eman.githupmvi.data.net.GithubInterActor
import com.eman.githupmvi.base.BaseViewModel
import com.eman.githupmvi.data.action.MainScreenAction
import com.eman.githupmvi.data.result.MainScreenResult

class HomeViewModel(
    private val gitInterActor: GithubInterActor
) : BaseViewModel<MainViewState, MainScreenAction, MainScreenResult>() {

     val internalViewState: MainViewState = MainViewState()

    override fun handle(action: MainScreenAction): LiveData<MainScreenResult> = liveData {
        when (action) {
            is MainScreenAction.GetGithubList -> emit(gitInterActor.getGithubList())
        }
    }

    override fun reduce(result: MainScreenResult): MainViewState {
        return when (result) {
            is MainScreenResult.DataState -> internalViewState.copy(
                data = result.data,
                dataFailure = null,
                isLoading = false
            )
            is MainScreenResult.LoadingState -> internalViewState.copy(isLoading = true)
            is MainScreenResult.ErrorState -> internalViewState.copy(
                dataFailure = result.error,
                isLoading = false
            )
        }
    }

}


