package com.tonynowater.uniforminvoicehelper.util

import android.util.Log
import com.tonynowater.uniforminvoicehelper.BuildConfig

/**
 * Created by tonyliao on 2018/3/14.
 */
object SLog {
    //雙冒號表示reflection
    private val TAG : String? = this@SLog.javaClass.simpleName
    private val debug = BuildConfig.DEBUG

    fun d(msg: String?, tag: String? = TAG) {
        if (debug) {
            Log.d(tag, msg.orEmpty())
        }
    }

    fun i(msg: String?, tag: String? = TAG) {
        if (debug) {
            Log.i(tag, msg.orEmpty())
        }
    }

    fun w(msg: String?, tag: String? = TAG) {
        if (debug) {
            Log.w(tag, msg.orEmpty())
        }
    }

    fun e(msg: String?, tag: String? = TAG) {
        if (debug) {
            Log.e(tag, msg.orEmpty())
        }
    }
}