plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
}

task("clean") {
    delete(rootProject.buildDir)
}

buildscript {
    dependencies {
        classpath(libs.test.jacoco.android)
    }
}

allprojects {
    tasks.withType<Test> {
        useJUnitPlatform()
    }
}
