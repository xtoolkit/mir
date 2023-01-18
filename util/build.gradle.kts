plugins {
    id("mir.android.library")
}

android {
    namespace = "io.github.xtoolkit.mir.util"
}

dependencies {
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.activity)
    implementation(libs.android.themeadapter)
    implementation(libs.android.systemuicontroller)
}
