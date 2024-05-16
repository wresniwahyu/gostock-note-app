pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "note app"
include(":app")
include(":core:data")
include(":core:network")
include(":core:ui")
include(":core:util")
include(":feature:note")
include(":core:local")
