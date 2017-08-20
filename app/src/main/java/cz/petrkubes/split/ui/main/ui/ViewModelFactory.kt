package cz.petrkubes.split.ui.main.ui

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import cz.petrkubes.split.ui.main.di.MainComponent

/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 20/08/2017
 */
class ViewModelFactory(val app: App) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>?): T {
        val t: T = super.create(modelClass)
        if (t is MainComponent.injectable) {
            (t as MainComponent.injectable).inject(app.component)
        }
        return t;
    }

}
