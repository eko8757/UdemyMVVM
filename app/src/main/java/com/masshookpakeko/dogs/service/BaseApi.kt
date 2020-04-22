package com.masshookpakeko.dogs.service

import com.masshookpakeko.dogs.model.ResponseDog
import io.reactivex.Single
import retrofit2.http.GET

interface BaseApi {

    @GET("DevTides/DogsApi/master/dogs.json")
    fun getData() : Single<List<ResponseDog>>
}