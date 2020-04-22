package com.masshookpakeko.dogs.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.masshookpakeko.dogs.model.DogBreed

class DetailViewModel: ViewModel() {

    val detailViewModel = MutableLiveData<DogBreed>()

    fun fetch() {
        val dog = DogBreed("1", "Corgi", "15 years", "breedGroup", "breedFor", "temperament", "")
        detailViewModel.value = dog
    }
}