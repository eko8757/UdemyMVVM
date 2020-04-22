package com.masshookpakeko.dogs.service

import com.masshookpakeko.dogs.model.ResponseDog
import com.masshookpakeko.dogs.utils.Constants
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiService {

    private val api = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(BaseApi::class.java)

    fun getData() : Single<List<ResponseDog>> {
        return api.getData()
    }
}