package com.androidapps.composeMVVM.app.utils

import com.androidapps.composeMVVM.domain.model.GetUserListItem
import com.androidapps.composeMVVM.data.database.ItemEntity

fun List<GetUserListItem>.toItemEntry(): List<ItemEntity> {
    return map { userList ->
        ItemEntity(
            id = userList.id!!.toLong(),
            name = userList.login!!,
            description = userList.url!!
            // Add other properties as needed
        )
    }
}
fun List<ItemEntity>.toUserList(): List<GetUserListItem> {
    return map { itemEntry ->
        GetUserListItem(
            id = itemEntry.id.toInt(),
            login = itemEntry.name,
            url = itemEntry.description
            // Add other properties as needed
        )
    }
}