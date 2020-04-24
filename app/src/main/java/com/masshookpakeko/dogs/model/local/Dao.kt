package com.masshookpakeko.dogs.model.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.masshookpakeko.dogs.model.ResponseDog

@Dao
interface Dao {

    @Insert
    suspend fun insertAll(vararg dogs: ResponseDog) : List<Long>

    @Query("SELECT * FROM responsedog")
    suspend fun getAllDogs(): List<ResponseDog>

    @Query("SELECT * FROM responsedog WHERE uuid = :dogId")
    suspend fun getDog(dogId: Int) : ResponseDog

    @Query("DELETE FROM responsedog")
    suspend fun deleteAllDogs()
}