plugins {
    id("mir.android.library")
}

dependencies {
    implementation(project(":util"))
    implementation(project(":util:core"))
    implementation(project(":user:core"))
    implementation(project(":playground:core"))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.material3)
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    implementation(libs.koin.android)
    implementation(libs.koin.compose)
}
