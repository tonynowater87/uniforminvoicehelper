package com.tonynowater.uniforminvoicehelper.util

import android.graphics.Bitmap
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder

/**
 * Created by tonyliao on 2018/3/19.
 */
object SBarCodeImageGenerator {

    fun generateBarCodeImage(content: String, width: Int = 400, height: Int = 400):BarCodeItem = BarCodeItem(
            content
            , BarcodeEncoder().encodeBitmap(content
                , BarcodeFormat.CODE_39
                , width
                , height))


    data class BarCodeItem(val content: String, val bitmap: Bitmap)
}