package com.eman.githupmvi.data.action

import com.eman.githupmvi.base.Action

sealed class MainScreenAction : Action {
    object GetGithubList : MainScreenAction()
}