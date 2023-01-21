plugins {
    id("mir.android.library.core")
}

android {
    namespace = "io.github.xtoolkit.mir.playground.core"
}

dependencies {
    implementation(project(":util:core"))
}
