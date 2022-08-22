package io.github.xtoolkit.mir.framework.di

import androidx.room.Room
import io.github.xtoolkit.mir.framework.db.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val appDatabasesModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java,
            "room_app.db"
        ).build()
    }

    single { get<AppDatabase>().userDao() }
    single { get<AppDatabase>().historyDao() }
    single { get<AppDatabase>().playgroundDao() }
    single { get<AppDatabase>().levelDao() }
    single { get<AppDatabase>().levelPlaygroundDao() }
}