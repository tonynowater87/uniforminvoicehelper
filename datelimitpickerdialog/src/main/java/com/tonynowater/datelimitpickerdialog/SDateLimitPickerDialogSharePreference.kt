package com.tonynowater.datelimitpickerdialog

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by tonyliao on 2018/4/12.
 */
class SDateLimitPickerDialogSharePreference {

    companion object {
        private const val SP_FILE_NAME = "SDateLimitPickerDialog"
        private const val SP_START_DATE_KEY = "SDateLimitPickerDialog_SP_START_DATE_KEY"
        private const val SP_END_DATE_KEY = "SDateLimitPickerDialog_SP_END_DATE_KEY"

        private var mInstance: SDateLimitPickerDialogSharePreference? = null

        fun getInstance(context: Context): SDateLimitPickerDialogSharePreference {
            if (mInstance == null) {
                mInstance = SDateLimitPickerDialogSharePreference(context)
            }
            return mInstance!!
        }
    }

    private var sharePref: SharedPreferences

    constructor(context: Context) {
        sharePref = context.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE)
    }

    fun putStartDate(value: String) {
        sharePref.edit().putString(SP_START_DATE_KEY, value).apply()
    }

    fun getStartDate(defValue: String = ""): String = sharePref.getString(SP_START_DATE_KEY, defValue)

    fun putEndDate(value: String) {
        sharePref.edit().putString(SP_END_DATE_KEY, value).apply()
    }

    fun getEndDate(defValue: String = ""): String = sharePref.getString(SP_END_DATE_KEY, defValue)

}