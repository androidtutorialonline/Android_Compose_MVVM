package com.androidapps.composeMVVM.data.model.receivedEvents

data class Payload(
    val action: String,
    val before: String,
    val commits: List<Commit>,
    val description: String,
    val distinct_size: Int,
    val head: String,
    val master_branch: String,
    val number: Int,
    val pull_request: PullRequest,
    val push_id: Long,
    val pusher_type: String,
    val ref: String,
    val ref_type: String,
    val repository_id: Int,
    val size: Int
)