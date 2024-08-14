package com.androidapps.composeMVVM

import com.androidapps.composeMVVM.app.MyApp
import com.androidapps.composeMVVM.data.ApiResponse
import com.androidapps.composeMVVM.data.ApiService
import com.androidapps.composeMVVM.domain.model.GithubUserList
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import io.cucumber.java.Before
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Rule
import retrofit2.Response
import java.util.Locale
import javax.inject.Inject

@HiltAndroidTest
class GitHubApiSteps {

    //@BeforeStep
    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var apiService: ApiService
    private lateinit var response: Response<List<GithubUserList>>

    @Before
    fun setUp() {
        // This injects the dependencies into this test class
        // `apiService` will be automatically injected
        hiltRule.inject()
        //MyApp::class.java.getDeclaredMethod("inject", Any::class.java).invoke(null, this)
    }

    @Given("the GitHub API endpoint is {string}")
    fun the_GitHub_API_endpoint_is(endpoint: String?) {
        // Endpoint is used in the When step
    }

    @When("a {string} request is made")
    @Throws(Exception::class)
    fun a_request_is_made(method: String) {
        runBlocking {
            response =
                when (method.uppercase(Locale.getDefault())) {
                "GET" -> apiService.getUserList()

                else -> throw IllegalArgumentException("Invalid HTTP method: $method")
            }
        }
    }

    @Then("the response status code should be {int}")
    fun the_response_status_code_should_be(statusCode: Int) {
        Assert.assertEquals(statusCode.toLong(), response.code().toLong())
    }

    @When("a <method> request is made")
    fun aMethodRequestIsMade() {
    }

}

