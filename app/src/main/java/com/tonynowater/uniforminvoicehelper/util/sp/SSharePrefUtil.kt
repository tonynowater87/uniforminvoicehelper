package com.tonynowater.uniforminvoicehelper.util.sp

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.GsonBuilder
import com.tonynowater.uniforminvoicehelper.SApplication

/**
 * Created by tonyliao on 2018/3/15.
 */
object SSharePrefUtil {

    private const val SP_FILE_NAME = "SP_FILE_NAME"

    private val sharePref: SharedPreferences = SApplication.mInstance.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE)

    fun putString(key: String, value: String) {
        sharePref.edit().putString(key, value).apply()
    }

    fun getString(key: String, defValue: String = ""): String = sharePref.getString(key, defValue)

    fun putInt(key: String, value: Int) {
        sharePref.edit().putInt(key, value).apply()
    }

    fun getInt(key: String, defValue: Int = 0): Int = sharePref.getInt(key, defValue)

    fun putJsonStringFromObj(key: String, obj: Any) {
        putString(key, GsonBuilder().create().toJson(obj))
    }

    fun <T> getObjFromJson(key: String, clazz: Class<T>): T {
        val gson = GsonBuilder().create()
        val json = getString(key)
        val obj = gson.fromJson<T>(json, clazz)
        return if (obj == null) {
            val initialObj = clazz.newInstance()
            putJsonStringFromObj(key, initialObj!!)
            initialObj
        } else {
            obj
        }
    }
}