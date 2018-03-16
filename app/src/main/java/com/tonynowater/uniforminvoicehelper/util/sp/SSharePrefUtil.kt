package com.tonynowater.uniforminvoicehelper.util.sp

import android.content.Context
import android.content.SharedPreferences
import com.tonynowater.uniforminvoicehelper.SApplication

/**
 * Created by tonyliao on 2018/3/15.
 */
object SSharePrefUtil {

    private const val SP_FILE_NAME = "SP_FILE_NAME"

    private val sharePref: SharedPreferences = SApplication.context.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE)

    fun putString(key: String, value: String) {
        sharePref.edit().putString(key, value).apply()
    }

    fun getString(key: String, defValue: String = "") = sharePref.getString(key, defValue)
}