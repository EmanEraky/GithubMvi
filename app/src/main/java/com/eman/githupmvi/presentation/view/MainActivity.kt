package com.eman.githupmvi.presentation.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.eman.githupmvi.R
import com.eman.githupmvi.data.action.MainScreenAction
import com.eman.githupmvi.data.net.GithubInterActor
import com.eman.githupmvi.models.GithubOwner
import com.eman.githupmvi.presentation.viewmodel.HomeViewModel
import com.eman.githupmvi.presentation.viewmodel.MainViewState
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: SearchResultAdapter

    private lateinit var mainViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = HomeViewModel(GithubInterActor())
        observeOnViewState()

        adapter = SearchResultAdapter(layoutInflater)
        adapter.setHasStableIds(true)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        getUsers()
    }

    private fun getUsers() {
        mainViewModel dispatch MainScreenAction.GetGithubList
    }

    private fun observeOnViewState() {
        mainViewModel.viewState.observe(this, Observer {
            render(it)
        })
    }

    private fun render(viewState: MainViewState?) {
        renderLoadingState(viewState?.isLoading ?: false)
        renderDataState(viewState?.data)
        renderErrorState(viewState?.dataFailure)
    }

    private fun renderLoadingState(loading: Boolean) {
        if (loading) {
            recyclerView.isEnabled = false
            loadingView.visibility = View.VISIBLE
        } else {
            recyclerView.isEnabled = true
            loadingView.visibility = View.GONE
        }
    }

    private fun renderDataState(dataState: List<GithubOwner>?) {
        if (!dataState.isNullOrEmpty()) {
            adapter.items = dataState
            adapter.notifyDataSetChanged()
        }
    }

    private fun renderErrorState(dataFailure: Exception?) {
        dataFailure?.let {
            Toast.makeText(this, dataFailure.localizedMessage, Toast.LENGTH_LONG).show()
        }
    }

}