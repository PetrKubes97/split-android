package cz.petrkubes.split.ui.main.di.modules

import android.app.Application
import dagger.Module
import javax.inject.Inject

/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 16/08/2017
 */
@Module
class CoreModule @Inject constructor(val application: Application)
