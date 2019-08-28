package tech.ntic.utils

import android.app.Activity
import android.content.Context

class AppConstants(context: Context) {

    val context : Context? = context

    companion object {

        const val STATUS = "status"
        const val ROOT_DIRECTORY_NAME = "ntic"
        const val ENG_LANG = "en"
        const val AM_LANG = "arm"
        const val START_ZERO_VALUE = "0"
        const val DATABASE_NAME = "ntic.db"
        const val LIST_SCROLLING = 10
        const val PREF_NAME = "tech.ntic.utils.Constants_pref"
        @JvmField val VERBOSE_NOTIFICATION_CHANNEL_NAME: CharSequence =
            "Verbose WorkManager Notifications"
        const val VERBOSE_NOTIFICATION_CHANNEL_DESCRIPTION =
            "Shows notifications whenever work starts"
        const val CHANNEL_ID = "VERBOSE_NOTIFICATION"
        const val NOTIFICATION_ID = 1
        const val FORGOT = "forgot"
        const val PASSWORD = "password"
        const val EMAIL = "email"
        const val USERNAME = "username"
    }

    fun saveString(key : String, value : String){
        val editor =context?.getSharedPreferences("Settings", Context.MODE_PRIVATE)?.edit()
        editor?.putString(key, value)
        editor?.apply()
    }

    fun getString(key: String) : String? {
        val sharedPreferences =context?.getSharedPreferences("Settings", Activity.MODE_PRIVATE)
        return sharedPreferences?.getString(key, "")
    }

    fun saveBoolean(key : String, value : Boolean){
        val editor =context?.getSharedPreferences("Settings", Context.MODE_PRIVATE)?.edit()
        editor?.putBoolean(key, value)
        editor?.apply()
    }

    fun getBoolean(key : String) : Boolean?{
        val sharedPreferences =context?.getSharedPreferences("Settings", Activity.MODE_PRIVATE)
        return sharedPreferences?.getBoolean(key, false)
    }
}