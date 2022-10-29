plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    id("jacoco")
}

task("clean") {
    delete(rootProject.buildDir)
}

allprojects {
    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

buildscript {
    dependencies {
        classpath(libs.test.jacoco)
    }
}