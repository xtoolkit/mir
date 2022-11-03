pluginManagement {
    includeBuild("build-logic")

    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)

    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Mir"
include(":app")
include(":util")
include(":util:core")
include(":user")
include(":user:core")
include(":level")
include(":level:core")
include(":playground")
include(":playground:core")
