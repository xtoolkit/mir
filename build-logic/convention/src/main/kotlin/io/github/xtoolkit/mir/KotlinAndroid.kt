package io.github.xtoolkit.mir

import com.android.build.api.dsl.CommonExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import com.android.build.gradle.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.provideDelegate
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

internal fun Project.configureKotlinAndroid(baseAppModuleExtension: BaseAppModuleExtension) {
    configureKotlinAndroid(baseAppModuleExtension as CommonExtension<*, *, *, *>)
    baseAppModuleExtension.apply {
        defaultConfig {
            targetSdk = 32
            lint.checkReleaseBuilds = false

            vectorDrawables {
                useSupportLibrary = true
            }
        }

        packagingOptions {
            resources {
                excludes += "/META-INF/{AL2.0,LGPL2.1}"
            }
        }

        testOptions {
            unitTests {
                isIncludeAndroidResources = true
            }
        }
    }
}

internal fun Project.configureKotlinAndroid(libraryExtension: LibraryExtension) {
    configureKotlinAndroid(libraryExtension as CommonExtension<*, *, *, *>)
    libraryExtension.apply {
        defaultConfig {
            targetSdk = 32
        }
    }
}

internal fun Project.configureKotlinAndroid(commonExtension: CommonExtension<*, *, *, *>) {
    commonExtension.apply {
        compileSdk = 32

        defaultConfig {
            minSdk = 21
        }

        buildTypes {
            getByName("debug") {
                isTestCoverageEnabled = true
                enableUnitTestCoverage = true
            }
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
            // isCoreLibraryDesugaringEnabled = true
        }

        kotlinOptions {
            val warningsAsErrors: String? by project
            allWarningsAsErrors = warningsAsErrors.toBoolean()
            jvmTarget = JavaVersion.VERSION_1_8.toString()
        }
    }

    val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

    dependencies {
        add("implementation", libs.findLibrary("androidx.core").get())
    }
}

fun CommonExtension<*, *, *, *>.kotlinOptions(block: KotlinJvmOptions.() -> Unit) {
    (this as ExtensionAware).extensions.configure("kotlinOptions", block)
}
