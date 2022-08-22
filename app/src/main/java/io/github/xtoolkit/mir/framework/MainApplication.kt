package io.github.xtoolkit.mir.framework

import android.app.Application
import com.google.android.material.color.DynamicColors
import io.github.xtoolkit.mir.framework.di.appDatabasesModule
import io.github.xtoolkit.mir.level.framework.levelKoinModules
import io.github.xtoolkit.mir.playground.framework.playgroundKoinModules
import io.github.xtoolkit.mir.user.framework.userKoinModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        DynamicColors.applyToActivitiesIfAvailable(this)

        startKoin {
            androidContext(this@MainApplication)
            modules(
                appDatabasesModule,
                *userKoinModules,
                *playgroundKoinModules,
                *levelKoinModules
            )
        }
    }
}