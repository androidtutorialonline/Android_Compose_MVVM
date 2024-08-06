plugins {

    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.hilt.android)
    id("kotlin-kapt")
}

android {
    namespace = "com.androidapps.composeMVVM"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.androidapps.composeMVVM"
        testApplicationId = "com.androidapps.composeMVVM.test"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "com.androidapps.composeMVVM.test.Runner"
        //testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }

    testOptions {
        //execution 'ANDROIDX_TEST_ORCHESTRATOR'
        packagingOptions {
            jniLibs {
                useLegacyPackaging = true
            }
        }
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "META-INF/gradle/incremental.annotation.processors"
            excludes += "META-INF/LICENSE.md"
            excludes += "META-INF/LICENSE-notice.md"
            //META-INF/LICENSE-notice.md
        }
    }
    sourceSets {
        getByName("main") {
            assets {
                srcDirs("src\\main\\assets", "src\\main\\assets")
            }
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.runner)
    //implementation(libs.androidx.constraintlayout)
    //androidTestImplementation(platform(libs.androidx.compose.bom))

    implementation(libs.coil)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Kotlin
    implementation(libs.kotlin.stdlib)

    implementation(libs.androidx.hilt.navigation.compose)
    implementation (libs.hilt.android)
    kapt (libs.hilt.android.compiler)

    // For instrumentation testing
    androidTestImplementation (libs.hilt.android.testing)
    kaptAndroidTest (libs.hilt.compiler)

    // For unit testing
    testImplementation (libs.hilt.android.testing)
    kaptTest (libs.hilt.compiler)


    // Retrofit and Moshi for API integration
    implementation(libs.retrofit)
    implementation(libs.converter.moshi)
    implementation(libs.moshi)
    implementation(libs.moshi.kotlin)
    implementation (libs.logging.interceptor)

    //implementation 'com.squareup.retrofit2:converter-gson:2.9.0'

    // Lifecycle components for MVVM
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.runtime.livedata)

    // Coroutines and Flow
    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)
    // ViewBinding
    implementation(libs.viewbinding)

    implementation(libs.androidx.room.runtime)
    kapt(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)

    implementation(libs.timber)

    // For Jetpack Compose
    implementation(libs.ui)
    implementation(libs.androidx.material)
    implementation(libs.ui.tooling)

    //implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    androidTestImplementation(libs.mockito.core)
    testImplementation(libs.mockito.kotlin)
    androidTestImplementation(libs.mockito.android)
    kaptAndroidTest(libs.mockito.android)
    testImplementation(libs.mockito.inline) // Replace with the latest version
    androidTestImplementation(libs.mockito.kotlin) // Replace with the latest version

    androidTestImplementation(libs.androidx.espresso.core)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    testImplementation(libs.kotlinx.coroutines.test)
    // Jetpack Compose UI Test dependency for Android instrumented tests
    androidTestImplementation(libs.ui.test.junit4) // Replace with the latest version

    androidTestImplementation(libs.mockk.android) // Replace with the latest version
    androidTestImplementation(libs.mockk.agent) // Replace with the latest version
    testImplementation(libs.mockk.agent.jvm) // Replace with the latest version

    // Cucumber
    testImplementation (libs.cucumber.java)
    testImplementation (libs.cucumber.junit)
    testImplementation (libs.cucumber.android)

    androidTestImplementation(libs.cucumber.android)
    androidTestImplementation(libs.cucumber.picocontainer)
    androidTestImplementation(libs.cucumber.java)
    androidTestImplementation(libs.cucumber.junit)

    androidTestImplementation(libs.reporting.plugin)
    testImplementation(libs.reporting.plugin)

    // Cucumber
    testImplementation(libs.cucumber.android) // Replace with the latest version
    testImplementation(libs.cucumber.junit)

    // Espresso and AndroidX Test
    androidTestImplementation(libs.androidx.espresso.core)


    // JUnit dependency (if not already included)
    androidTestImplementation(libs.junit)

    implementation(libs.ui)
    implementation(libs.androidx.foundation)
    implementation(libs.androidx.material)
    implementation(libs.androidx.constraintlayout.compose)

}