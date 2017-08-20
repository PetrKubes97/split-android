package cz.petrkubes.split.ui.main.ui

import android.app.Application
import cz.petrkubes.split.ui.main.di.modules.CoreModule
import cz.petrkubes.split.ui.main.di.DaggerMainComponent
import cz.petrkubes.split.ui.main.di.MainComponent

/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 16/08/2017
 */
class App: Application() {

    lateinit var component: MainComponent

    override fun onCreate() {
        super.onCreate()
        component = DaggerMainComponent.builder().coreModule(CoreModule(this)).build()
    }

    fun nothing() {

    }
}
