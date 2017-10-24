package cz.petrkubes.split.ui.main.ui

import android.app.Application
import com.facebook.stetho.Stetho
import com.raizlabs.android.dbflow.config.FlowManager
import cz.petrkubes.split.ui.main.di.DaggerMainComponent
import cz.petrkubes.split.ui.main.di.MainComponent
import cz.petrkubes.split.ui.main.di.modules.CoreModule



/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 16/08/2017
 */
class App: Application() {

    lateinit var component: MainComponent

    override fun onCreate() {
        super.onCreate()
        // Dagger
        component = DaggerMainComponent.builder().coreModule(CoreModule(this)).build()

        // DBFLow
        FlowManager.init(this)

        // Stetho
        Stetho.initializeWithDefaults(this);
    }
}
