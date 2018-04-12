package com.tonynowater.datelimitpickerdialog

import android.content.Context
import android.support.design.widget.TabLayout
import android.support.v7.widget.RecyclerView
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.*

/**
 * Created by tonyliao on 2018/4/10.
 */
class UiScaleUtil {

    companion object {
        private var mInstance: UiScaleUtil? = null

        private var m_iMaxLayoutHeightWeight = 0   //長度單位最大值
        private var m_iMaxLayoutWidthWeight = 0    //寬度單位最大值
        private var m_fHeightUnit = 0f             //長度單位
        private var m_fWidthUnit = 0f              //寬度單位
        private var m_fFontUnit = 0f               //字型單位
        private var m_fScreenScaleDensity = 0f     //螢幕密度

        private lateinit var m_Dm: DisplayMetrics  //取的螢幕解析度

        fun getInstance(context: Context): UiScaleUtil {
            if (mInstance == null) {
                mInstance = UiScaleUtil()
                initial(context)
            }

            return mInstance!!
        }

        private fun initial(context: Context) {
            //設定「長度、寬度單位」最大值
            m_iMaxLayoutHeightWeight = context.resources.getInteger(R.integer.activity_weight_sum_vertical)
            m_iMaxLayoutWidthWeight = context.resources.getInteger(R.integer.activity_weight_sum_horizontal)

            //設定「長度、寬度、字型單位」
            m_Dm = DisplayMetrics()
            val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            windowManager.defaultDisplay.getMetrics(m_Dm)

            m_fHeightUnit = m_Dm.heightPixels.toFloat() / m_iMaxLayoutHeightWeight
            m_fWidthUnit = m_Dm.widthPixels.toFloat() / m_iMaxLayoutWidthWeight
            m_fFontUnit = Math.min(m_fHeightUnit, m_fWidthUnit)
            m_fScreenScaleDensity = m_Dm.scaledDensity
        }

    }

    /**
     * 將Instance設為null，讓前端可以重算版面大小
     */
    fun setInstanceNull() {
        mInstance = null
    }

    /**
     * @param dHeight 高度比例，參數範圍為「1 ~ 640」
     * @return 取出經長寬比運算出的高度
     */
    private fun getLayoutHeight(dHeight: Double): Int {
        return (dHeight * m_fHeightUnit).toInt()
    }

    /**
     * @param dWeight 寬度比例，參數範圍為「1 ~ 360」
     * @return 取出經長寬比運算出的寬度
     */
    private fun getLayoutWidth(dWeight: Double): Int {
        return (dWeight * m_fWidthUnit).toInt()
    }

    /**
     * @param dWeight 單位比例，抓取寬高比最小的值，請小心使用
     * @return 取出經長寬比運算出的寬度
     */
    private fun getLayoutMinUnit(dWeight: Double): Int {
        return (dWeight * m_fFontUnit).toInt() // 高的UNIT一般較小(因按鍵列跟系統列的關係)，所以同U數要設正方形要用同一個getLayout，不然會變扁
    }

    /**
     * @param iTextLength 總行數
     * @return 取出經長寬比運算出的總行高度
     */
    fun getLayoutHeightByTextSize(dTextSizeDefine: Double, iTextLength: Int): Int {
        if (iTextLength <= 0) {
            return 0
        }

        val iLayoutHeight = getTextSize(dTextSizeDefine) * iTextLength
        val iMaxLayoutHeight = getLayoutHeight(m_iMaxLayoutHeightWeight.toDouble())
        return if (iMaxLayoutHeight <= iLayoutHeight) {
            iMaxLayoutHeight
        } else iLayoutHeight

    }

    /**
     * @param iTextLength 總字數
     * @return 取出經長寬比運算出的總字寬度
     */
    fun getLayoutWidthByTextSize(dTextSizeDefine: Double, iTextLength: Int): Int {
        if (iTextLength <= 0) {
            return 0
        }

        val iLayoutWidth = getTextSize(dTextSizeDefine) * iTextLength
        val iMaxLayoutWidth = getLayoutWidth(m_iMaxLayoutWidthWeight.toDouble())
        return if (iMaxLayoutWidth <= iLayoutWidth) {
            iMaxLayoutWidth
        } else iLayoutWidth
    }

    /**
     * @return 取出字型大小
     */
    fun getTextSize(dTextSizeDefine: Double): Int {
        return (dTextSizeDefine * m_fFontUnit).toInt()
    }

    /**
     * 設定字型大小-TextView
     * @param textView
     */
    private fun setTextSize(dTextSizeDefine: Double, textView: TextView) {
        textView.includeFontPadding = false
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getTextSize(dTextSizeDefine).toFloat())
    }

    /**
     * 設定字型大小-Button
     * @param button
     */
    fun setTextSize(dTextSizeDefine: Double, button: Button) {
        button.includeFontPadding = false
        button.setTextSize(TypedValue.COMPLEX_UNIT_PX, getTextSize(dTextSizeDefine).toFloat())
    }

    /**
     * 設定字型大小-EditText
     * @param editText
     */
    fun setTextSize(dTextSizeDefine: Double, editText: EditText) {
        editText.includeFontPadding = false
        editText.setTextSize(TypedValue.COMPLEX_UNIT_PX, getTextSize(dTextSizeDefine).toFloat())
    }

    /**
     * 設定 View 經長寬比運算出的 Margins
     */
    fun setMargins(view: View, left: Double, top: Double, right: Double, bottom: Double) {
        if (view.layoutParams is ViewGroup.MarginLayoutParams) {
            if (left != 0.0 && top == 0.0 && right == 0.0 && bottom == 0.0) {
                (view.layoutParams as ViewGroup.MarginLayoutParams).marginStart = getLayoutWidth(left)
                return
            }
            if (left == 0.0 && top == 0.0 && right != 0.0 && bottom == 0.0) {
                (view.layoutParams as ViewGroup.MarginLayoutParams).marginEnd = getLayoutWidth(right)
                return
            }

            (view.layoutParams as ViewGroup.MarginLayoutParams).setMargins(
                    getLayoutWidth(left),
                    getLayoutHeight(top),
                    getLayoutWidth(right),
                    getLayoutHeight(bottom))
        } else {
            throw RuntimeException("This view can not be set margins")
        }
    }

    /**
     * 設定 View 經長寬比運算出的 Padding
     */
    fun setPadding(view: View, left: Double, top: Double, right: Double, bottom: Double) {
        view.setPadding(
                getLayoutWidth(left),
                getLayoutHeight(top),
                getLayoutWidth(right),
                getLayoutHeight(bottom))
    }

    /**
     * 設定 View 經長寬比運算出的長寬
     */
    private fun setViewSize(view: View, width: Double, height: Double) {
        //某些手機才會發現這個問題...
        if (width >= 0) {
            view.layoutParams.width = getLayoutWidth(width)
        } else {
            view.layoutParams.width = width.toInt()
        }
        if (height >= 0) {
            view.layoutParams.height = getLayoutHeight(height)
        } else {
            view.layoutParams.height = height.toInt()
        }
    }

    private fun selfAdjustSameScaleView(v: View, width: Double, height: Double) {
        v.layoutParams.height = getLayoutMinUnit(height)
        v.layoutParams.width = getLayoutMinUnit(width)
    }

    /**
     * 設定TabLayout的寬高
     */
    fun selfAdjustTextSizeForTabLayout(tabLayout: TabLayout,
                                       textSize: Int) {
        try {
            val field = TabLayout::class.java.getDeclaredField("mTabTextSize")
            val field2 = TabLayout::class.java.getDeclaredField("mTabTextMultiLineSize")
            field.isAccessible = true
            field2.isAccessible = true
            field.setInt(tabLayout, getTextSize(textSize.toDouble()))
            field2.setInt(tabLayout, getTextSize(textSize.toDouble()))
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun selfAdjustAllView(rootView: View) {
        if (isNormalViewGroup(rootView)) {
            for (i in 0 until (rootView as ViewGroup).childCount) {
                selfAdjustAllView(rootView.getChildAt(i))
            }
        }

        adjustMargin(rootView)
        if (rootView is ImageView) {
            //只要是imageView就設定長寬同比例
            selfAdjustSameScaleView(rootView,
                    rootView.getLayoutParams().width.toDouble(),
                    rootView.getLayoutParams().height.toDouble())
        } else {
            adjustViewSize(rootView)
        }

        adjustTextSize(rootView)
        adjustPadding(rootView)
    }

    private fun adjustViewSize(rootView: View) {
        setViewSize(
                rootView,
                rootView.layoutParams.width.toDouble(),
                rootView.layoutParams.height.toDouble())
    }

    private fun adjustTextSize(rootView: View) {
        if (rootView is TextView) {
            setTextSize(rootView.textSize.toDouble(), rootView)
        }
    }

    private fun adjustMargin(rootView: View) {
        if (rootView.layoutParams is ViewGroup.MarginLayoutParams) {
            (rootView.layoutParams as ViewGroup.MarginLayoutParams).marginStart = getLayoutWidth((rootView.layoutParams as ViewGroup.MarginLayoutParams).marginStart.toDouble())
            (rootView.layoutParams as ViewGroup.MarginLayoutParams).marginEnd = getLayoutWidth((rootView.layoutParams as ViewGroup.MarginLayoutParams).marginEnd.toDouble())
            (rootView.layoutParams as ViewGroup.MarginLayoutParams).setMargins((rootView.layoutParams as ViewGroup.MarginLayoutParams).marginStart,
                    getLayoutHeight((rootView.layoutParams as ViewGroup.MarginLayoutParams).topMargin.toDouble()),
                    (rootView.layoutParams as ViewGroup.MarginLayoutParams).marginEnd,
                    getLayoutHeight((rootView.layoutParams as ViewGroup.MarginLayoutParams).bottomMargin.toDouble()))

        }
    }

    private fun isNormalViewGroup(rootView: View): Boolean {
        return (rootView is ViewGroup
                && rootView !is ListView
                && rootView !is Spinner
                && rootView !is RecyclerView
                && rootView !is TabLayout
                && rootView !is DatePicker
                && rootView !is TimePicker)
    }

    private fun adjustPadding(rootView: View) {
        if (rootView !is Spinner) {
            if (rootView.isPaddingRelative) {
                rootView.setPaddingRelative(
                        getLayoutWidth(rootView.paddingStart.toDouble()),
                        getLayoutHeight(rootView.paddingTop.toDouble()),
                        getLayoutWidth(rootView.paddingEnd.toDouble()),
                        getLayoutHeight(rootView.paddingBottom.toDouble()))
            } else {
                setPadding(
                        rootView,
                        rootView.paddingLeft.toDouble(),
                        rootView.paddingTop.toDouble(),
                        rootView.paddingRight.toDouble(),
                        rootView.paddingBottom.toDouble())
            }
        }
    }
}
