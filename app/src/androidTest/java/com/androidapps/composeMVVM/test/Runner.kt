package com.androidapps.composeMVVM.test

import android.app.Application
import android.content.Context
import dagger.hilt.android.testing.HiltTestApplication
import io.cucumber.junit.Cucumber
import io.cucumber.android.runner.CucumberAndroidJUnitRunner
import io.cucumber.junit.CucumberOptions
import org.junit.runner.RunWith

@RunWith(Cucumber::class)
@CucumberOptions(
    features = ["features"],
    glue = ["com.androidapps.composeMVVM"],
    plugin = ["pretty", "de.monochromata.cucumber.report.PrettyReports:target/pretty-cucumber"]
    //plugin = ["pretty", "me.jvt.cucumber:report.PrettyReports:/sdcard/Download/cucumber-reports"]
    //plugin = ["pretty", "me.jvt.cucumber:report.PrettyReports:/data/data/com.androidapps.composeMVVM/cache/cucumber-reports/"]
)

class Runner : CucumberAndroidJUnitRunner() {


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