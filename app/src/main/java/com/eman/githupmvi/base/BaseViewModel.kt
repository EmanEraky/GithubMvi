package com.eman.githupmvi.base

import androidx.lifecycle.*

abstract class BaseViewModel<S : ViewState, A : Action, R : Result> : ViewModel() {

    abstract fun handle(action: A): LiveData<R>

    abstract fun reduce(result: R): S

    private val nextAction = MutableLiveData<A>()

    val viewState: LiveData<S> = nextAction
        .switchMap {
            handle(it)
        }.map {
            reduce(it)
        }
    infix fun dispatch(action: A) {

        nextAction.value = action
    }

}