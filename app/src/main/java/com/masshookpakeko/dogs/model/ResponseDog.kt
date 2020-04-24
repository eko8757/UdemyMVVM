package com.masshookpakeko.dogs.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
data class ResponseDog(

    @ColumnInfo(name = "bred_for")
    @SerializedName("bred_for")
    @Expose
    val bredFor: String?,

    @ColumnInfo(name = "breed_group")
    @SerializedName("breed_group")
    @Expose
    val breedGroup: String?,

//    @ColumnInfo(name = "breed_height")
//    @SerializedName("height")
//    @Expose
//    val height: ResponseDogHeight,

    @ColumnInfo(name = "breed_id")
    @SerializedName("id")
    @Expose
    val id: Int?,

    @ColumnInfo(name = "life_span")
    @SerializedName("life_span")
    @Expose
    val lifeSpan: String?,

    @ColumnInfo(name = "breed_name")
    @SerializedName("name")
    @Expose
    val name: String?,

    @ColumnInfo(name = "breed_origin")
    @SerializedName("origin")
    @Expose
    val origin: String?,

    @ColumnInfo(name = "breed_temperament")
    @SerializedName("temperament")
    @Expose
    val temperament: String?,

//    @ColumnInfo(name = "breed_weight")
//    @SerializedName("weight")
//    @Expose
//    val weight: ResponseDogWeight,

    @ColumnInfo(name = "breed_url")
    @SerializedName("url")
    @Expose
    val url: String? = null,

    @ColumnInfo(name = "country_code")
    @SerializedName("country_code")
    @Expose
    val countryCode: String?
) {
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
}