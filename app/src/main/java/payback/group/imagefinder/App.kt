package payback.group.imagefinder

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import payback.group.imagefinder.di.appDiModule

class App:Application (){
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(appDiModule)
        }

    }
}