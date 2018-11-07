package be.equality.metar.ui

import android.arch.lifecycle.MutableLiveData
import android.view.View
import be.equality.metar.base.BaseViewModel
import be.equality.metar.network.MetarApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MetarViewModel : BaseViewModel() {

    /**
     * The instance of the MetarApi class
     * to get back the results of the API
     */
    @Inject
    lateinit var metarApi : MetarApi

    /**
     * Indicates whether the loading fragment should be displayed.
     */
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()


    /**
     * Represents a disposable resources
     */
    private  var subscription: Disposable

    init {
        subscription = metarApi.getMetar("EBOS")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe{ onRetrieveMetarStart()}
                .doOnTerminate { onRetrieveMetarFinish()}
                .subscribe(
                        {onRetrieveMetarSucces()},
                        {onRetrieveMetarError()}
                )
    }

    private fun onRetrieveMetarError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun onRetrieveMetarSucces() {

    }

    private fun onRetrieveMetarFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun onRetrieveMetarStart() {
        loadingVisibility.value = View.VISIBLE
    }

    /**
     * Disposes the subscription when the [BaseViewModel] is no longer used.
     */
    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }


}