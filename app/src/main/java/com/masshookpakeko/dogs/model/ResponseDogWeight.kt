package com.masshookpakeko.dogs.model

import androidx.room.ColumnInfo
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseDogWeight(

    @ColumnInfo(name = "breed_imperial")
    @SerializedName("imperial")
    @Expose
    val imperial: String?,

    @ColumnInfo(name = "breed_metric")
    @SerializedName("metric")
    @Expose
    val metric: String?
)