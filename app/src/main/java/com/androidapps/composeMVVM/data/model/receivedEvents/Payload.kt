package com.androidapps.composeMVVM.data.model.receivedEvents

data class Payload(
    val action: String? = "",
    val before: String? = "",
    val commits: List<Commit>? = emptyList(),
    val description: String? = "",
    val distinct_size: Int? = 0,
    val head: String? = "",
    val master_branch: String? = "",
    val number: Int? = 0,
    val pull_request: PullRequest? = PullRequest(),
    val push_id: Long? = 0,
    val pusher_type: String? = "",
    val ref: String? = "",
    val ref_type: String? = "",
    val repository_id: Int? = 0,
    val size: Int = 0
)