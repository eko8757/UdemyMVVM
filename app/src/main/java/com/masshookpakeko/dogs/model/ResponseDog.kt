package com.masshookpakeko.dogs.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseDog(

    @SerializedName("bred_for")
    @Expose
    val bredFor: String?,
    @SerializedName("breed_group")
    @Expose
    val breedGroup: String?,
    @SerializedName("height")
    @Expose
    val height: ResponseDogHeight?,
    @SerializedName("id")
    @Expose
    val id: Int?,
    @SerializedName("life_span")
    @Expose
    val lifeSpan: String?,
    @SerializedName("name")
    @Expose
    val name: String?,
    @SerializedName("origin")
    @Expose
    val origin: String?,
    @SerializedName("temperament")
    @Expose
    val temperament: String?,
    @SerializedName("weight")
    @Expose
    val weight: ResponseDogWeight?,
    @SerializedName("url")
    @Expose
    val url: String? = null,
    @SerializedName("country_code")
    @Expose
    val countryCode: String?
)