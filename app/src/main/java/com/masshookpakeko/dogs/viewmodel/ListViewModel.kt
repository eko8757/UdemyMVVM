package com.masshookpakeko.dogs.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.masshookpakeko.dogs.model.ResponseDog
import com.masshookpakeko.dogs.model.local.DogDatabase
import com.masshookpakeko.dogs.service.ApiService
import com.masshookpakeko.dogs.utils.SharedPreferenceHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class ListViewModel(application: Application) : BaseViewModel(application) {

    private val dogService = ApiService()
    private val disposable = CompositeDisposable()
    private var prefHelper = SharedPreferenceHelper(getApplication())
    private var refreshTime = 5 * 60 * 1000 * 1000 * 1000L

    val dogs = MutableLiveData<List<ResponseDog>>()
    val dogsLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh() {
        val updateTime = prefHelper.getUpdateTime()

        if (updateTime != null && updateTime != 0L && System.nanoTime() -  updateTime < refreshTime) {
            fromDatabase()
        } else {
            fromRemote()
        }
        fromRemote()
    }

    fun refreshFromPassCache() {
        fromRemote()
    }

    private fun fromDatabase() {
        loading.value = true
        launch {
            val dogs = DogDatabase(getApplication()).dogDao().getAllDogs()
            dogRetrieveData(dogs)
            Toast.makeText(getApplication(), "Dogs retrieve from database", Toast.LENGTH_SHORT).show()
        }
    }

    private fun fromRemote() {
        loading.value = true
        disposable.add(
            dogService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<ResponseDog>>() {
                    override fun onSuccess(t: List<ResponseDog>) {
                        storeToLocally(t)
                        Toast.makeText(getApplication(), "Dogs retrieve from remote", Toast.LENGTH_SHORT).show()
                    }

                    override fun onError(e: Throwable) {
                        dogsLoadError.value = true
                        loading.value = false
                        e.printStackTrace()
                    }
                })
        )
    }

    private fun dogRetrieveData(t: List<ResponseDog>) {
        dogs.value = t
        dogsLoadError.value = false
        loading.value = false
    }

    private fun storeToLocally(list: List<ResponseDog>) {
        launch {
            val dao = DogDatabase(getApplication()).dogDao()
            dao.deleteAllDogs()
            val result = dao.insertAll(*list.toTypedArray())
            var i = 0
            while (i < list.size) {
                list[i].uuid = result[i].toInt()
                i++
            }

            dogRetrieveData(list)
        }

        prefHelper.saveUpdate(System.nanoTime())
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }


}