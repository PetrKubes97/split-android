package cz.petrkubes.split.ui.main.ui.main

import android.arch.lifecycle.ViewModel
import cz.petrkubes.split.ui.main.di.MainComponent

/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 06/08/2017
 */
class MainActivityViewModel : ViewModel(), MainComponent.injectable {

    override fun inject(mainComponent: MainComponent) {
        mainComponent.inject(this)
    }

}
