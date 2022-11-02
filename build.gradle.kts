plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.test.coveraged) apply true
}

rootCoverage {
    generateXml = true
}

task("clean") {
    delete(rootProject.buildDir)
}
