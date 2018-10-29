package be.equality.metar

import android.support.v4.app.Fragment
import be.equality.metar.fragments.AirportsFragment
import be.equality.metar.fragments.DetailsFragment
import be.equality.metar.fragments.OldmetarsFragment
import be.equality.metar.fragments.RawFragment


/**
 * Class which helps with the BottomNavigation View.
 *
 * Sauce: https://github.com/yasszu/bottom-navigation
 */
enum class BottomNavigationPosition(val position: Int, val id: Int) {
    HOME(0, R.id.navigation_item_airportlist),
    DETAILS(1, R.id.navigation_item_details),
    RAW(2, R.id.navigation_item_raw),
    OLD(3, R.id.navigation_item_oldmetars);
}

fun findNavigationPositionById(id: Int): BottomNavigationPosition = when (id) {
    BottomNavigationPosition.HOME.id -> BottomNavigationPosition.HOME
    BottomNavigationPosition.DETAILS.id -> BottomNavigationPosition.DETAILS
    BottomNavigationPosition.RAW.id -> BottomNavigationPosition.RAW
    BottomNavigationPosition.OLD.id -> BottomNavigationPosition.OLD
    else -> BottomNavigationPosition.HOME
}

fun BottomNavigationPosition.createFragment(): Fragment = when (this) {
    BottomNavigationPosition.HOME -> AirportsFragment.newInstance()
    BottomNavigationPosition.OLD -> OldmetarsFragment.newInstance()
    BottomNavigationPosition.RAW -> RawFragment.newInstance()
    BottomNavigationPosition.DETAILS -> DetailsFragment.newInstance()
}

fun BottomNavigationPosition.getTag(): String = when (this) {
    BottomNavigationPosition.HOME -> AirportsFragment.TAG
    BottomNavigationPosition.DETAILS ->DetailsFragment.TAG
    BottomNavigationPosition.RAW -> RawFragment.TAG
    BottomNavigationPosition.OLD -> OldmetarsFragment.TAG


}