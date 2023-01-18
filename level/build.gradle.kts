plugins {
    id("mir.android.library")
}

android {
    namespace = "io.github.xtoolkit.mir.level"
}

dependencies {
    implementation(project(":util"))
    implementation(project(":util:core"))
    implementation(project(":level:core"))
    implementation(project(":user"))
    implementation(project(":user:core"))
    implementation(project(":playground"))
    implementation(project(":playground:core"))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.activity)
    implementation(libs.androidx.compose.material3)
    implementation(libs.android.pager)
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    implementation(libs.koin.android)
    implementation(libs.koin.compose)
}
