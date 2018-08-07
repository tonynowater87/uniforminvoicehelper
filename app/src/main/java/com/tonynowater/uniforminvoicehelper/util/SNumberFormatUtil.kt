package com.tonynowater.uniforminvoicehelper.util

import java.text.DecimalFormat
import java.util.*

/**
 * Created by tonyliao on 2018/8/7.
 */
object SNumberFormatUtil {

    private val simpleFormatter = DecimalFormat.getInstance(Locale.TAIWAN)

    fun formatNumberWithThousand(number : String) = simpleFormatter.format(number.toLong())!!
}