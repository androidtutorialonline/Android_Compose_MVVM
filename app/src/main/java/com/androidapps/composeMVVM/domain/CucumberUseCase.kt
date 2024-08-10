package com.androidapps.composeMVVM.domain

import com.androidapps.composeMVVM.data.ApiResponse
import com.androidapps.composeMVVM.domain.model.GithubUserList
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

open class CucumberUseCase @Inject constructor(
    private val repository: CucumberRepository
) {

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

    open fun getEndpoint(): Flow<ApiResponse<List<GithubUserList>>> {
        return repository.getEndpoint()
    }
    open fun postEndpoint(): Flow<ApiResponse<List<GithubUserList>>> {
        return repository.postEndpoint()
    }
    open fun deleteEndpoint(): Flow<ApiResponse<List<GithubUserList>>> {
        return repository.deleteEndpoint()
    }
}
