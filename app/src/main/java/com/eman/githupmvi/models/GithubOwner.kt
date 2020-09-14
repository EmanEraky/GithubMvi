package com.eman.githupmvi.models

import com.google.gson.annotations.SerializedName

data class GithubOwner(
    @SerializedName("avatar_url") var avatar: String,
    @SerializedName("login") var name: String
)