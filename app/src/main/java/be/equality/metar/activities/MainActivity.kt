package be.equality.metar.activities

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity;
import be.equality.metar.fragments.AirportsFragment


import kotlinx.android.synthetic.main.activity_main.*
import android.support.annotation.NonNull
import android.support.design.widget.BottomNavigationView
import android.view.MenuItem
import be.equality.metar.*
import be.equality.metar.fragments.DetailsFragment
import be.equality.metar.fragments.OldmetarsFragment
import be.equality.metar.fragments.RawFragment
import com.orhanobut.logger.Logger
import com.orhanobut.logger.AndroidLogAdapter


/**
 * Recourse naming questions? : see [https://jeroenmols.com/blog/2016/03/07/resourcenaming/]
 */

class MainActivity : AppCompatActivity(), AirportsFragment.OnFragmentInteractionListener, DetailsFragment.OnFragmentInteractionListener,
OldmetarsFragment.OnFragmentInteractionListener,RawFragment.OnFragmentInteractionListener{


    /**
     * Last position which has been clicked
     */
    private var navPosition: BottomNavigationPosition = BottomNavigationPosition.HOME

    /**
     * Extension function to the FragmentManager which accepts a Lambda with Receiver as its argument, whereas the FragmentTransaction is the Receiver.
     * Here the parameter func is an Extension function to the FragmentTransaction
     * and it has zero parameters and returns a [FragmentTransaction]. We invoke that function after calling
     * the beginTransaction() and end the transaction by calling commit().
     *
     * Sauce: [https://medium.com/thoughts-overflow/how-to-add-a-fragment-in-kotlin-way-73203c5a450b]
     */
    inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
        val fragmentTransaction = beginTransaction()
        fragmentTransaction.func()
        fragmentTransaction.commit()
    }

    /**
     * Extension functions to the AppCompatActivity which adds a fragment
     *
     * Sauce: [https://medium.com/thoughts-overflow/how-to-add-a-fragment-in-kotlin-way-73203c5a450b]
     */
    fun AppCompatActivity.addFragment(fragment: Fragment, frameId: Int) {
        supportFragmentManager.inTransaction { add(frameId, fragment) }
    }

    /**
     * Extension functions to the AppCompatActivity which replaces a fragment
     *
     * Sauce: [https://medium.com/thoughts-overflow/how-to-add-a-fragment-in-kotlin-way-73203c5a450b]
     */
    fun AppCompatActivity.replaceFragment(fragment: Fragment, frameId: Int) {
        supportFragmentManager.inTransaction { replace(frameId, fragment) }
    }


    /**
     * Creates this Activity.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Logger.addLogAdapter(AndroidLogAdapter())
        Logger.i("Testing")
        setSupportActionBar(toolbar_main)
        // During initial setup, plug in the details fragment.
        if (savedInstanceState == null) {
            addFragment(AirportsFragment.newInstance(), R.id.framelayout_main_placeholder)
            Logger.i("Added the fragment on initialisation")
        }
    }

    /**
     * Adding the listener to the [BottomNavigationView] of this Activity.
     *
     * Why?: Sauce: [https://www.techyourchance.com/android-activity-life-cycle-for-professional-developers/]
     */
    override fun onStart() {
        super.onStart()
        bottomnavigationview_metardetail_navigation.setOnNavigationItemSelectedListener { item ->
            navPosition = findNavigationPositionById(item.itemId)
             switchFragment(navPosition)
        }
    }

    /**
     * Deattaching the listener of the BottomNavigation
     * Why?: Sauce: [https://www.techyourchance.com/android-activity-life-cycle-for-professional-developers/]
     */
    override fun onStop() {
        super.onStop()
        bottomnavigationview_metardetail_navigation.setOnNavigationItemReselectedListener { null }
    }

    override fun showAirportMetar() {
        Logger.i("Showing the METAR")
    }

    override fun detailsClicked() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun oldMetarsClicked() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun rawFragmentClicker() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * Finds the fragment which needs to be shown. If it exists, it replaces the current fragment.
     * If it does not find the fragment (i.e. it has not been added before), the [BottomNavigationPosition]
     * creates the Fragment corresponding to the selected position and replaces it.
     *
     * Sauce: [https://github.com/yasszu/bottom-navigation]
     */
    private fun switchFragment(navPosition: BottomNavigationPosition): Boolean {
        return supportFragmentManager.findFragment(navPosition).let {
            if (it.isAdded) return false
            replaceFragment(it, R.id.framelayout_main_placeholder)
            true
        }
    }

    /**
     * Tries to find a fragment in the fragmentmanager. If it cannot find the fragment, the
     * [BottomNavigationPosition] creates the appropriate fragment.
     *
     * Sauce: [https://github.com/yasszu/bottom-navigation]
     */
    private fun FragmentManager.findFragment(position: BottomNavigationPosition): Fragment {
        return findFragmentByTag(position.getTag()) ?: position.createFragment()
    }


}
