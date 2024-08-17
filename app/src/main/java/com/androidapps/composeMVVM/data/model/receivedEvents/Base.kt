package com.androidapps.composeMVVM.data.model.receivedEvents

data class Base(
    val label: String? = "",
    val ref: String? = "",
    val repo: Repo? = Repo(),
    val sha: String? = "",
    val user: UserXX = UserXX()
)