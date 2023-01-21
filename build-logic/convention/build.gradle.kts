plugins {
    `kotlin-dsl`
}

group = "io.github.xtoolkit.mir.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "mir.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }

        register("androidLibrary") {
            id = "mir.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }

        register("androidLibraryCore") {
            id = "mir.android.library.core"
            implementationClass = "AndroidLibraryCoreConventionPlugin"
        }
    }
}
