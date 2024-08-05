package com.androidapps.composeMVVM
import com.androidapps.composeMVVM.data.database.UserEntity
import com.androidapps.composeMVVM.domain.GetUserUseCase
import com.androidapps.composeMVVM.domain.ItemRepository
import com.androidapps.composeMVVM.app.utils.toItemEntry
import com.androidapps.composeMVVM.app.utils.toUserList
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class GetItemsUseCaseTest {

    @Mock
    private lateinit var repository: ItemRepository

    private lateinit var getItemsUseCase: GetUserUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        getItemsUseCase = GetUserUseCase(repository)
    }

    @Test
    fun `should return list of items`() = runBlocking {
        // Arrange
        val expectedItems = listOf(
            UserEntity(id = 1, name = "Item 1", description = "Description 1"),
            UserEntity(id = 2, name = "Item 2", description = "Description 2")
        )

        // Mock repository response
        `when`(repository.getUserList()).thenReturn(flowOf(expectedItems.toUserList()))

        // Act
        var items = emptyList<UserEntity>()
        getItemsUseCase().collect {
            items = it.toItemEntry()
        }

        // Assert
        assertEquals(expectedItems, items)
    }
}
