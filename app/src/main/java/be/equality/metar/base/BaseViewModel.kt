package be.equality.metar.base

import android.arch.lifecycle.ViewModel
import be.equality.metar.injection.component.DaggerViewModelInjector
import be.equality.metar.injection.component.ViewModelInjector
import be.equality.metar.injection.module.NetworkModule
import be.equality.metar.ui.MetarViewModel

/**
 * Base class for the ViewModels, as we will use it for dependency injection only.
 */
abstract class BaseViewModel : ViewModel() {



    private val injector: ViewModelInjector = DaggerViewModelInjector
            .builder()
            .networkModule(NetworkModule)
            .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is MetarViewModel -> injector.inject(this)
        }
    }

}