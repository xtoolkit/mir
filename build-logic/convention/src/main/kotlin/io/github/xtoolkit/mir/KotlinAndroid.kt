package io.github.xtoolkit.mir

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.provideDelegate
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

/**
 * Configure base Kotlin with Android options
 */
internal fun Project.configureKotlinAndroid(
    commonExtension: CommonExtension<*, *, *, *>,
    isLibrary: Boolean = false
) {
    commonExtension.apply {
        compileSdk = 32

        defaultConfig {
            minSdk = 21

            if (!isLibrary) {
                lint.checkReleaseBuilds = false

                vectorDrawables {
                    useSupportLibrary = true
                }
            }
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
            // isCoreLibraryDesugaringEnabled = true
        }

        kotlinOptions {
            // Treat all Kotlin warnings as errors (disabled by default)
            // Override by setting warningsAsErrors=true in your ~/.gradle/gradle.properties
            val warningsAsErrors: String? by project
            allWarningsAsErrors = warningsAsErrors.toBoolean()

            /*
            freeCompilerArgs = freeCompilerArgs + listOf(
                "-opt-in=kotlin.RequiresOptIn",
                "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                "-opt-in=kotlinx.coroutines.FlowPreview",
                "-opt-in=kotlin.Experimental",
                "-opt-in=kotlinx.serialization.ExperimentalSerializationApi"
            )
             */

            // Set JVM target to 1.8
            jvmTarget = JavaVersion.VERSION_1_8.toString()
        }

        if (!isLibrary) {
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

    val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

    dependencies {
        add("implementation", libs.findLibrary("androidx.core").get())
    }
}

fun CommonExtension<*, *, *, *>.kotlinOptions(block: KotlinJvmOptions.() -> Unit) {
    (this as ExtensionAware).extensions.configure("kotlinOptions", block)
}
