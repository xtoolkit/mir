plugins {
    id("mir.android.library.core")
}

android {
    namespace = "io.github.xtoolkit.mir.user.core"
}

dependencies {
    implementation(project(":util:core"))
    implementation(project(":playground:core"))
}
