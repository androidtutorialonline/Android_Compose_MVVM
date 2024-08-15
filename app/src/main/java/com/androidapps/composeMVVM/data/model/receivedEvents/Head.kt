package com.androidapps.composeMVVM.data.model.receivedEvents

data class Head(
    val label: String,
    val ref: String,
    val repo: Repo,
    val sha: String,
    val user: UserXX
)