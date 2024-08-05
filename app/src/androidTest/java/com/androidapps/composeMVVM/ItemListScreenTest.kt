package com.androidapps.composeMVVM

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.androidapps.composeMVVM.app.utils.NetworkConnection
import com.androidapps.composeMVVM.app.utils.toUserList
import com.androidapps.composeMVVM.data.database.UserEntity
import com.androidapps.composeMVVM.domain.ItemRepository
import com.androidapps.composeMVVM.presentation.ItemListScreen
import com.androidapps.composeMVVM.presentation.MainActivity
import com.androidapps.composeMVVM.presentation.viewModel.ItemViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class ItemListScreenTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @MockK
    private lateinit var repository: ItemRepository

    @MockK
    private lateinit var networkConnection: NetworkConnection

    @Before
    fun setup() {
        repository = mockk()
        // Create a mock instance of NetworkConnection
        networkConnection = mockk()
        hiltRule.inject() // Initialize Hilt dependencies
    }

    @Test
    fun items_displayed() {
        // Arrange
        val items = listOf(UserEntity(1, "Item 1", "Description 1"))


        // Mock behavior
        //`when`(repository.getUserList()).thenReturn(flowOf(items.toUserList()))

        // Act
        composeTestRule.setContent {
            ItemListScreen(ItemViewModel(FakeGetUserUseCase(items.toUserList(), repository), networkConnection))
        }

        // Assert
        composeTestRule.onNodeWithText("Item 1").assertIsDisplayed()
    }
}
