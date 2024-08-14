package com.androidapps.composeMVVM.data.model.receivedEvents

data class receivedEventsListItem(
    val actor: Actor,
    val created_at: String,
    val id: String,
    val org: Org,
    val payload: Payload,
    val `public`: Boolean,
    val repo: RepoXX,
    val type: String
)