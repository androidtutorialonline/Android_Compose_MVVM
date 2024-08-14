package com.androidapps.composeMVVM.data.model.receivedEvents

data class Commit(
    val author: Author,
    val distinct: Boolean,
    val message: String,
    val sha: String,
    val url: String
)