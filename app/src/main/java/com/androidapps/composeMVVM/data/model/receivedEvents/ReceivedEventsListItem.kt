package com.androidapps.composeMVVM.data.model.receivedEvents

data class ReceivedEventsListItem(
    val actor: Actor? = Actor(),
    val created_at: String? = "",
    val id: String? = "",
    val org: Org? = Org(),
    val payload: Payload? = Payload(),
    val `public`: Boolean? = false,
    val repo: Repo? = Repo(),
    val type: String = ""
)