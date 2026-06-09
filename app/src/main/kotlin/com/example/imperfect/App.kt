package com.example.imperfect

import android.app.Application
import android.util.Log
import com.example.imperfect.core.database.seed.DatabaseSeeder
import com.example.imperfect.core.database.seed.LookupSeeder
import com.example.imperfect.core.di.AppModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        Thread.setDefaultUncaughtExceptionHandler { thread, throwable ->
            Log.e("GLOBAL_CRASH", "CRASH THREAD=${thread.name}", throwable)
        }

        val koin = startKoin {
            androidContext(this@App)
            modules(AppModule.modules)
        }.koin

        CoroutineScope(Dispatchers.IO).launch {
            val lookupSeeder = koin.get<LookupSeeder>()
            val dbSeeder = koin.get<DatabaseSeeder>()

            lookupSeeder.seed()
            dbSeeder.seed()
        }
    }
}