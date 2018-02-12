package cz.petrkubes.split.ui.main.ui

import android.app.Application
import android.os.StrictMode
import com.facebook.stetho.Stetho
import com.raizlabs.android.dbflow.config.FlowManager
import cz.petrkubes.split.ui.main.di.DaggerMainComponent
import cz.petrkubes.split.ui.main.di.MainComponent





/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 16/08/2017
 */
class App: Application() {

    lateinit var component: MainComponent

    override fun onCreate() {
        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build())
        StrictMode.setVmPolicy(StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects()
                .detectLeakedClosableObjects()
                .penaltyLog()
                .penaltyDeath()
                .build())

        super.onCreate()
        // Dagger
        component = DaggerMainComponent.builder().build()

        // DBFLow
        FlowManager.init(this)

        // Stetho
        Stetho.initializeWithDefaults(this);
    }
}
