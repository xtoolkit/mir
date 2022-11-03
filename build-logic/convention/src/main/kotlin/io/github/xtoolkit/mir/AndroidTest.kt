package io.github.xtoolkit.mir

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

internal fun Project.configureAndroidTest(
    commonExtension: CommonExtension<*, *, *, *>
) {
    commonExtension.apply {
        defaultConfig {
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }
    }

    val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

    dependencies {
        add("implementation", libs.findLibrary("androidx.core").get())
        add("testImplementation", libs.findLibrary("test.kotlin").get())
        add("testImplementation", libs.findLibrary("test.junit").get())
        add("testImplementation", libs.findLibrary("test.mockk").get())
        add("testImplementation", libs.findLibrary("test.androidx.core").get())
        add("testImplementation", libs.findLibrary("test.koin.core").get())
        add("testImplementation", libs.findLibrary("test.koin.junit").get())
        add("androidTestImplementation", libs.findLibrary("test.compose").get())
        add("androidTestDebugImplementation", libs.findLibrary("debug.compose").get())
    }
}

internal fun Project.configureUnitTest(
    commonExtension: CommonExtension<*, *, *, *>
) {
    commonExtension.apply {
        defaultConfig {
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }
    }

    val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

    dependencies {
        add("implementation", libs.findLibrary("androidx.core").get())
        add("testImplementation", libs.findLibrary("test.kotlin").get())
        add("testImplementation", libs.findLibrary("test.junit").get())
        add("testImplementation", libs.findLibrary("test.mockk").get())
    }
}
