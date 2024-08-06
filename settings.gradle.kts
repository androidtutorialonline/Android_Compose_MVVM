pluginManagement {
    repositories {
        /*google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }*/
        google()
        maven { url = uri("https://plugins.gradle.org/m2/") }
        gradlePluginPortal()
        mavenCentral()

    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        maven { url = uri("https://plugins.gradle.org/m2/") }
        mavenCentral()
    }
}

rootProject.name = "ComposeMVVM"
include(":app")
 