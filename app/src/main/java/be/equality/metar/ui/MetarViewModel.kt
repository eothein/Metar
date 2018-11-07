package be.equality.metar.ui

import be.equality.metar.base.BaseViewModel
import be.equality.metar.network.MetarApi
import javax.inject.Inject

class MetarViewModel : BaseViewModel() {

    /**
     * The instance of the MetarApi class
     * to get back the results of the API
     */
    @Inject
    lateinit var metarApi : MetarApi




}