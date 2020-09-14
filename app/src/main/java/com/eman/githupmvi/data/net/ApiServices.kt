package com.eman.githupmvi.data.net


import com.eman.githupmvi.models.GithubOwner
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

open class ApiServices {
    fun providesSearchEngine(): GithubBackend {
        val retrofit = Retrofit.Builder().baseUrl("https://api.github.com")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().serializeNulls().create()
                )
            )
            .build()

        return retrofit.create(GithubBackend::class.java)
    }

    interface GithubBackend {
        @GET("/users")
        suspend fun getRepositories(): List<GithubOwner>
    }

}