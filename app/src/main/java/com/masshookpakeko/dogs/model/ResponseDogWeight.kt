package com.masshookpakeko.dogs.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseDogWeight(
    @SerializedName("imperial")
    @Expose
    val imperial: String?,
    @SerializedName("metric")
    @Expose
    val metric: String?
)