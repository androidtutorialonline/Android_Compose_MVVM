package com.androidapps.composeMVVM

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import dagger.hilt.android.testing.HiltTestApplication
import io.cucumber.junit.Cucumber
//import io.cucumber.android.runner.CucumberAndroidJUnitRunner
import io.cucumber.junit.CucumberOptions
import org.junit.runner.RunWith

/*@RunWith(Cucumber::class)
@CucumberOptions(
    features = ["features"],
    glue = ["com.androidapps.composeMVVM."],
    plugin = ["pretty"]
)*/

class Runner : AndroidJUnitRunner() {

    @Throws(
        InstantiationException::class,
        IllegalAccessException::class,
        ClassNotFoundException::class
    )
    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?,
    ): Application {
        return super.newApplication(cl, HiltTestApplication::class.java.name, context)
    }

}