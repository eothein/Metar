package be.equality.metar.activities

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity;



import android.support.design.widget.BottomNavigationView
import android.support.v4.app.FragmentPagerAdapter
import be.equality.metar.*
import be.equality.metar.fragments.*
import com.orhanobut.logger.Logger
import com.orhanobut.logger.AndroidLogAdapter
import kotlinx.android.synthetic.main.main_layout.*


/**
 * Recourse naming questions? : see [https://jeroenmols.com/blog/2016/03/07/resourcenaming/]
 */

class MainActivity : AppCompatActivity(), AirportsFragment.OnFragmentInteractionListener, DetailsFragment.OnFragmentInteractionListener,
OldmetarsFragment.OnFragmentInteractionListener,RawFragment.OnFragmentInteractionListener{



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
        setContentView(R.layout.main_layout)
        Logger.addLogAdapter(AndroidLogAdapter())
        Logger.i("Testing")
        //setSupportActionBar()

    }

    /**
     * Adding the listener to the [BottomNavigationView] of this Activity.
     *
     * Why?: Sauce: [https://www.techyourchance.com/android-activity-life-cycle-for-professional-developers/]
     */
    override fun onStart() {
        super.onStart()

        navigation_main.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.navigation_item_airportlist -> {
                    viewpager_main.currentItem = BaseFragment.AIRPORTS
                    true
                }
                R.id.navigation_item_details -> {
                    viewpager_main.currentItem = BaseFragment.DETAILS
                    true
                }
                R.id.navigation_item_oldmetars -> {

                    viewpager_main.currentItem = BaseFragment.OLD
                    true
                }
                R.id.navigation_item_raw -> {
                    viewpager_main.currentItem = BaseFragment.RAW
                    true
                }
                else -> {
                    viewpager_main.currentItem = BaseFragment.AIRPORTS
                    true
                }
            }
        }

        viewpager_main.adapter = object : FragmentPagerAdapter(supportFragmentManager) {
            override fun getItem(p0: Int): Fragment {
                when(p0){
                    BaseFragment.AIRPORTS -> return AirportsFragment()
                    BaseFragment.RAW -> return RawFragment()
                    BaseFragment.DETAILS -> return DetailsFragment()
                    BaseFragment.OLD -> return OldmetarsFragment()
                }
                return AirportsFragment()
            }

            override fun getCount(): Int {
                return 4
            }
        }

    }

    /**
     * Deattaching the listener of the BottomNavigation
     * Why?: Sauce: [https://www.techyourchance.com/android-activity-life-cycle-for-professional-developers/]
     */
    override fun onStop() {
        super.onStop()
        navigation_main.setOnNavigationItemReselectedListener (null)
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







}
