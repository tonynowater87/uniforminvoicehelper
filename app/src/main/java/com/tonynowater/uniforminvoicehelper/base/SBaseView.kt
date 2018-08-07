package com.tonynowater.uniforminvoicehelper.base

import android.content.Context
import android.support.annotation.CallSuper
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout

/**
 * Created by tonyliao on 2018/8/7.
 */
abstract class SBaseView @JvmOverloads constructor(context: Context
                                                   , attrs: AttributeSet
                                                   , defStyleAttr: Int = 0) : LinearLayout(context, attrs, defStyleAttr) {

    abstract fun getLayoutResource(): Int

    init {
        LayoutInflater.from(context).inflate(getLayoutResource(), this, true)
    }
}