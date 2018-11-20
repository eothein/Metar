package be.equality.metar.base

import android.arch.lifecycle.ViewModel
import be.equality.metar.injection.component.DaggerViewModelComponent
import be.equality.metar.injection.component.ViewModelComponent
import be.equality.metar.injection.module.NetworkModule
import be.equality.metar.ui.MetarViewModel

/**
 * Base class for all ViewModels that require injection through Dagger.
 */
abstract class InjectedViewModel : ViewModel() {

    /**
     * An ViewModelComponent is required to do the actual injecting.
     * Every Component has a default builder to which you can add all
     * modules that will be needed for the injection.
     */
    private val injector: ViewModelComponent = DaggerViewModelComponent
            .builder()
            .networkModule(NetworkModule)
            .build()

    /**
     * Perform the injection when the ViewModel is created
     */
    init {
        inject()
    }

    /**
     * Injects the required dependencies.
     * We need the 'when(this)' construct for each new ViewModel as the 'this' reference should
     * refer to an instance of that specific ViewModel.
     * Just injecting into a generic InjectedViewModel is not specific enough for Dagger.
     */
    private fun inject() {
        when (this) {
            is MetarViewModel -> injector.inject(this)
        }
    }

}