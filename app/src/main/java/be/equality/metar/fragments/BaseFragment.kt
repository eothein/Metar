package be.equality.metar.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import com.orhanobut.logger.Logger

open class BaseFragment() : Fragment() {


    open var TAG : String = ""

    companion object {
        val AIRPORTS = 0
        val RAW = 1
        val DETAILS = 2
        val OLD = 3
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Logger.i("Created $TAG")

    }
}