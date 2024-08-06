package com.androidapps.composeMVVM.domain

import com.androidapps.composeMVVM.domain.model.GithubUserList
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

open class GetUserUseCase @Inject constructor(
    private val repository: ItemRepository
) {
    open operator fun invoke(): Flow<List<GithubUserList>> {

        /*
        // Business logic: e.g., filter out invalid items or modify data
        val validItems = items.filter { item ->
            // Example validation: only insert items with non-empty names
            item.name.isNotBlank()
        }

        if (validItems.isEmpty()) {
            throw IllegalArgumentException("No valid items to insert.")
        }

        // You could also add more complex logic here, such as
        // transforming the items, logging the operation, etc.

        // Insert the validated and potentially transformed items

        return repository.getUserList().map { response ->
            // You can manipulate the response if needed
            // For example, you might want to transform data or handle specific business rules
            response
        }
        */

        return repository.getUserList()
    }
}
