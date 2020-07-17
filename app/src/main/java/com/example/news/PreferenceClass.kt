package com.example.news

import android.content.Context
import android.content.SharedPreferences


class PreferenceClass(context: Context) {

    private val sharedpreferences: SharedPreferences

    companion object {
        const val PREFERENCE_NAME = "PREFERENCE_DATA"
    }

    init {
        sharedpreferences = context.getSharedPreferences(
            PREFERENCE_NAME,
            Context.MODE_PRIVATE
        )
    }


    fun getcount():Int{ return sharedpreferences.getInt("count", 0)}
       fun setcount(x:Int) {
            val editor = sharedpreferences.edit()
            editor.putInt("count", x)
            editor.apply()
        }

    fun clearCount() {
        val editor = sharedpreferences.edit()
        editor.remove("count")
        editor.apply()
    }


}