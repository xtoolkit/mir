plugins {
    id("mir.android.library.core")
}

android {
    namespace = "io.github.xtoolkit.mir.level.core"
}

dependencies {
    implementation(project(":util:core"))
    implementation(project(":playground:core"))
}
