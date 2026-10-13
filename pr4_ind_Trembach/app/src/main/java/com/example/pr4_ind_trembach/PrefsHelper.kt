package com.example.pr4_ind_trembach

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PrefsHelper(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    private val gson = Gson()

    fun saveUser(name: String) {
        prefs.edit().putString("user", name).apply()
    }

    fun getUser(): String {
        return prefs.getString("user", "") ?: ""
    }

    fun isLoggedIn(): Boolean = getUser().isNotEmpty()

    fun saveEntries(list: List<WeatherEntry>) {
        val json = gson.toJson(list)
        prefs.edit().putString("weather", json).apply()
    }

    fun getEntries(): List<WeatherEntry> {
        val json = prefs.getString("weather", "") ?: ""
        if (json.isEmpty()) return emptyList()
        val type = object : TypeToken<List<WeatherEntry>>() {}.type
        return gson.fromJson(json, type)
    }
}