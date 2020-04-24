package com.masshookpakeko.dogs.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.masshookpakeko.dogs.model.ResponseDog
import com.masshookpakeko.dogs.model.local.DogDatabase
import kotlinx.coroutines.launch

class DetailViewModel(application: Application) : BaseViewModel(application) {

    val detailViewModel = MutableLiveData<ResponseDog>()

    fun fetch(uuid: Int) {
        launch {
            val dog = DogDatabase(getApplication()).dogDao().getDog(uuid)
            detailViewModel.value = dog
        }
    }
}