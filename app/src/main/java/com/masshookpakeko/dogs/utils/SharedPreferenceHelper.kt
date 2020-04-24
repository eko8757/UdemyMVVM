package com.masshookpakeko.dogs.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.preference.PreferenceManager

class SharedPreferenceHelper {

    companion object {

        private const val PREF_TIME = "Prefs time"
        private var prefs: SharedPreferences? = null

        @Volatile private var instance : SharedPreferenceHelper? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) : SharedPreferenceHelper = instance ?: synchronized(LOCK) {
            instance ?: buildHelper(context).also {
                instance = it
            }
        }
        private fun buildHelper(context: Context) : SharedPreferenceHelper {
            prefs = PreferenceManager.getDefaultSharedPreferences(context)
            return SharedPreferenceHelper()
        }
    }

    fun saveUpdate(time: Long) {
        prefs?.edit(commit = true) {putLong(PREF_TIME, time)}
    }

    fun getUpdateTime() = prefs?.getLong(PREF_TIME, 0)
}