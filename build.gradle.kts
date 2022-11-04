buildscript {
    repositories {
        google()
        mavenCentral()
    }
}

rootCoverage {
    generateXml = true
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.test.coverage) apply true
}

task("clean") {
    delete(rootProject.buildDir)
}
