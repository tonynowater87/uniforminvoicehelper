package com.tonynowater.datelimitpickerdialog

import android.widget.DatePicker
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object SDateUtil {

    private const val DEFAULT_CE_DATE_FORMAT = "yyyy/MM/dd"

    private val mCalendar = Calendar.getInstance(TimeZone.getDefault(), Locale.TAIWAN)
    private val mSimpleDateFormat = SimpleDateFormat(DEFAULT_CE_DATE_FORMAT)

    fun getFirstMomentCalendarFromDatePicker(datePicker: DatePicker): Calendar {
        with(mCalendar) {
            mCalendar.set(Calendar.YEAR, datePicker.year)
            mCalendar.set(Calendar.MONTH, datePicker.month)
            mCalendar.set(Calendar.DAY_OF_MONTH, datePicker.dayOfMonth)
            mCalendar.set(Calendar.HOUR_OF_DAY, 0)
            mCalendar.set(Calendar.MINUTE, 0)
            mCalendar.set(Calendar.SECOND, 0)
            return mCalendar
        }
    }

    fun getLastMomentCalendarFromDatePicker(datePicker: DatePicker): Calendar {
        return with(mCalendar) {
            mCalendar.set(Calendar.YEAR, datePicker.year)
            mCalendar.set(Calendar.MONTH, datePicker.month)
            mCalendar.set(Calendar.DAY_OF_MONTH, datePicker.dayOfMonth)
            mCalendar.set(Calendar.HOUR_OF_DAY, 23)
            mCalendar.set(Calendar.MINUTE, 59)
            mCalendar.set(Calendar.SECOND, 59)
            mCalendar
        }
    }

    fun formatCalendarToString(calendar: Calendar) = mSimpleDateFormat.format(calendar.time)!!

    fun getCalendarFromString(dateString: String): Calendar? {
        return try {
            mCalendar.time = mSimpleDateFormat.parse(dateString)
            mCalendar
        } catch (e: ParseException) {
            null
        }
    }
}
