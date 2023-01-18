plugins {
    id("mir.android.application")
    kotlin("kapt")
}

android {
    defaultConfig {
        targetSdk = 33
        applicationId = "io.github.xtoolkit.mir"
        testApplicationId = "io.github.xtoolkit.mir.test"
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        getByName("debug") {
            applicationIdSuffix = ".debug"
        }
    }

    namespace = "io.github.xtoolkit.mir"
}

dependencies {
    implementation(project(":util"))
    implementation(project(":user"))
    implementation(project(":user:core"))
    implementation(project(":level"))
    implementation(project(":level:core"))
    implementation(project(":playground"))
    implementation(project(":playground:core"))
    implementation(libs.androidx.fragment)
    implementation(libs.androidx.collection)
    implementation(libs.androidx.lifecycle)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.activity)
    implementation(libs.androidx.compose.material3)
    implementation(libs.android.material)
    implementation(libs.android.themeadapter)
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    implementation(libs.koin.android)
    implementation(libs.koin.compose)
    annotationProcessor(libs.room.compiler)
    kapt(libs.room.compiler)
}

configurations.configureEach {
    resolutionStrategy {
        force(libs.test.junit)
    }
}
