package be.equality.metar.ui

import android.arch.lifecycle.MutableLiveData
import be.equality.metar.base.InjectedViewModel
import be.equality.metar.model.Metar
import be.equality.metar.network.MetarApi
import com.orhanobut.logger.Logger
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MetarViewModel : InjectedViewModel() {

    private val rawMetar = MutableLiveData<String>()

    /**
     * The instance of the MetarApi class
     * to get back the results of the API
     */
    @Inject
    lateinit var metarApi: MetarApi

    /**
     * Indicates whether the loading view should be displayed.
     */
    val loadingVisibility: MutableLiveData<Boolean> = MutableLiveData()

    /**
     * Represents a disposable resources
     */
    private var subscription: Disposable

    init {
        subscription = metarApi.getMetar("EBOS")
                //we tell it to fetch the data on background by
                .subscribeOn(Schedulers.io())
                //we like the fetched data to be displayed on the MainTread (UI)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetrieveMetarStart() }
                .doOnTerminate { onRetrieveMetarFinish() }
                .subscribe(
                        { result -> onRetrieveMetaSuccess(result) },
                        { error -> onRetrieveMetarError(error) }
                )

    }

    private fun onRetrieveMetarError(error: Throwable) {
        //Currently requests fail silently, which isn't great for the user.
        //It would be better to show a Toast, or maybe make a TextView visible with the error message.
        Logger.e(error.message!!)
    }

    private fun onRetrieveMetaSuccess(result: Metar) {
        rawMetar.value = result.rawMetar
        Logger.i(result.rawMetar)
    }

    private fun onRetrieveMetarFinish() {
        Logger.i("Finished retrieving METAR info")
        loadingVisibility.value = false
    }

    private fun onRetrieveMetarStart() {
        Logger.i("Started retrieving METAR info")
        loadingVisibility.value = true
    }

    /**
     * Disposes the subscription when the [InjectedViewModel] is no longer used.
     */
    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    fun getRawMetar(): MutableLiveData<String> {
        return rawMetar
    }

}