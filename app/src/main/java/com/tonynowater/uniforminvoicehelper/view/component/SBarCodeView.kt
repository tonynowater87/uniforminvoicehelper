package com.tonynowater.uniforminvoicehelper.view.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.tonynowater.uniforminvoicehelper.R
import com.tonynowater.uniforminvoicehelper.util.SBarCodeImageGenerator
import kotlinx.android.synthetic.main.view_bar_code.view.*

/**
 * Created by tonyliao on 2018/3/19.
 */
class SBarCodeView @JvmOverloads constructor(
        context: Context
        , attrs: AttributeSet? = null
        , defStyleAttr: Int = 0) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.view_bar_code, this, true)
    }

    fun setBarCodeItem(barCodeItem: SBarCodeImageGenerator.BarCodeItem) {
        image_view_bar_code.setImageBitmap(barCodeItem.bitmap)
        text_view_bar_code_content.text = barCodeItem.content
    }
}