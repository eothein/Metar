package be.equality.metar.fragments

import android.support.v4.app.Fragment

open class BaseFragment : Fragment() {


    open var TAG : String = ""

    companion object {
        const val AIRPORTS = 0
        const val RAW = 1
        const val DETAILS = 2
        const val OLD = 3
    }

}