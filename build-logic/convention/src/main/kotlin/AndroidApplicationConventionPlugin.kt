import com.android.build.api.variant.ApplicationAndroidComponentsExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import io.github.xtoolkit.mir.configureKotlinAndroid
import io.github.xtoolkit.mir.configureAndroidCompose
import io.github.xtoolkit.mir.configureUnitTest
import io.github.xtoolkit.mir.configureAndroidTest
import io.github.xtoolkit.mir.configureJacoco
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.gradle.jacoco")
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<BaseAppModuleExtension> {
                configureKotlinAndroid(this)
                configureAndroidCompose(this)
                configureUnitTest(this)
                configureAndroidTest(this)
            }

            configureJacoco(extensions.getByType<ApplicationAndroidComponentsExtension>())
        }
    }
}
