package com.tonynowater.datelimitpickerdialog

import android.support.v4.app.FragmentManager
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.DatePicker
import kotlinx.android.synthetic.main.dialog_date_picker.*
import java.util.*

/**
 * A native style date picker, can select a range of two dates
 *
 * Created by tonyliao on 2018/4/10.
 */
class SDateLimitPickerDialog : SBaseDialogFragment(), View.OnClickListener, DatePicker.OnDateChangedListener {

    interface OnDatePickerSelectListener {
        fun onDatePicked(dateItem: DateItem)
    }

    private var mStep = STEP_START_DATE
    private lateinit var mStartDate: Calendar
    private lateinit var mListener: OnDatePickerSelectListener

    override fun getLayoutId(): Int = R.layout.dialog_date_picker

    override fun initView(uiScaleUtil: SUiScaleUtil) {
        uiScaleUtil.selfAdjustAllView(root)
        initStartDatePicker()
        tv_dialog_confirm.setOnClickListener(this)
        tv_dialog_cancel.setOnClickListener(this)
        if (!isCancelable) {
            tv_dialog_cancel.visibility = View.GONE
        }
    }

    /**
     * @return DatePicker目前選取的日期
     */
    private val calendarFromDatePicker: Calendar
        get() = if (mStep == STEP_START_DATE) {
            SDateUtil.getFirstMomentCalendarFromDatePicker(date_picker_start)
        } else {
            SDateUtil.getLastMomentCalendarFromDatePicker(date_picker_end)
        }

    fun setListener(listener: OnDatePickerSelectListener) {
        mListener = listener
    }

    private fun initStartDatePicker() {
        val startDate = SDateLimitPickerDialogSharePreference.getInstance(context!!).getStartDate()
        if (!TextUtils.isEmpty(startDate)) {
            //帶入之前查詢的日期區間
            val calendar = SDateUtil.getCalendarFromString(startDate)
            date_picker_start.init(calendar!!.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), this)
        } else {
            //帶入今天
            val calendar = Calendar.getInstance()
            date_picker_start.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), this)
        }
        setStepOneMinAndMaxDate()
    }

    private fun setStepOneMinAndMaxDate() {
        val calendarStart = Calendar.getInstance()
        val calendarEnd = Calendar.getInstance()
        calendarStart.add(Calendar.MONTH, -3)
        date_picker_start.minDate = calendarStart.timeInMillis
        calendarEnd.add(Calendar.MONTH, 3)
        date_picker_start.maxDate = calendarEnd.timeInMillis
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.tv_dialog_confirm -> when (mStep) {
                STEP_START_DATE -> initToStep2()
                STEP_END_DATE -> {
                    val startDate = SDateUtil.formatCalendarToString(mStartDate)
                    val endDate = SDateUtil.formatCalendarToString(calendarFromDatePicker)
                    val dateItem = DateItem(startDate, endDate)
                    mListener.onDatePicked(dateItem)
                    SDateLimitPickerDialogSharePreference.getInstance(context!!).putStartDate(dateItem.startDate)
                    SDateLimitPickerDialogSharePreference.getInstance(context!!).putEndDate(dateItem.endDate)
                    dismiss()
                }
            }
            R.id.tv_dialog_cancel -> when (mStep) {
                STEP_START_DATE -> if (isCancelable) dismiss()
                STEP_END_DATE -> if (isCancelable) dismiss()
            }
        }
    }

    private fun initToStep2() {
        tv_dialog_date_picker_title.text = getString(R.string.date_picker_title_end_date)
        tv_dialog_confirm.text = getString(R.string.dialog_confirm_txt)
        mStartDate = calendarFromDatePicker
        initEndDatePicker()
        mStep = STEP_END_DATE
    }

    private fun initEndDatePicker() {
        date_picker_start.visibility = View.GONE
        date_picker_end.visibility = View.VISIBLE
        date_picker_end.init(mStartDate.get(Calendar.YEAR), mStartDate.get(Calendar.MONTH), mStartDate.get(Calendar.DAY_OF_MONTH), this)
        setStepTwoMinAndMaxDate()
    }

    private fun setStepTwoMinAndMaxDate() {
        date_picker_end!!.minDate = mStartDate.timeInMillis
        val calendar = mStartDate.clone() as Calendar
        calendar.add(Calendar.MONTH, 1)
        date_picker_end!!.maxDate = calendar.timeInMillis
    }

    override fun onDateChanged(view: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        Log.d("onDateChanged", String.format(Locale.TAIWAN, "onDateChanged:%d/%d/%d", year, monthOfYear, dayOfMonth))
        //因為直接切換年份有可能超出限制範圍，所以在做一次日期的判斷，
        //當日期 < 最小日期時，設定成最小日期
        //當日期 > 最小大期時，設定成最大日期
        val changeDate = Calendar.getInstance()
        changeDate.set(year, monthOfYear, dayOfMonth)
        val lChangeDate = changeDate.timeInMillis

        val minDate = Calendar.getInstance()
        minDate.timeInMillis = view.minDate
        val lminDate = minDate.timeInMillis

        val maxDate = Calendar.getInstance()
        maxDate.timeInMillis = view.maxDate
        val lmaxDate = maxDate.timeInMillis

        if (lChangeDate < lminDate) {
            view.init(minDate.get(Calendar.YEAR), minDate.get(Calendar.MONTH), minDate.get(Calendar.DAY_OF_MONTH), this)
        } else if (lChangeDate > lmaxDate) {
            view.init(maxDate.get(Calendar.YEAR), maxDate.get(Calendar.MONTH), maxDate.get(Calendar.DAY_OF_MONTH), this)
        }
    }

    companion object {
        const val STEP_START_DATE = 1
        const val STEP_END_DATE = 2
    }

    data class DateItem(val startDate: String, val endDate: String)

    class SDateLimitPickerDialogBuilder {

        private var mDialog: SDateLimitPickerDialog
        private var mFragmentManager: FragmentManager

        constructor(fragmentManager: FragmentManager) {
            mFragmentManager = fragmentManager
            mDialog = SDateLimitPickerDialog()
        }

        /**
         * 預設為true:可按返回鍵關閉
         */
        fun setCancelable(cancelable: Boolean): SDateLimitPickerDialogBuilder {
            mDialog.isCancelable = cancelable
            return this
        }

        fun addListener(listener: SDateLimitPickerDialog.OnDatePickerSelectListener): SDateLimitPickerDialogBuilder {
            mDialog.setListener(listener)
            return this
        }

        fun build(): SDateLimitPickerDialog {
            return mDialog
        }

        fun show() {
            mDialog.show(mFragmentManager, mDialog.javaClass.simpleName)
        }
    }
}
