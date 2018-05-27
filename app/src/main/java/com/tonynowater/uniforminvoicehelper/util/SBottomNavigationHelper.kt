package com.tonynowater.uniforminvoicehelper.util

import android.annotation.SuppressLint
import android.support.design.internal.BottomNavigationItemView
import android.support.design.internal.BottomNavigationMenuView
import android.support.design.widget.BottomNavigationView

/**
 * A helper to disable the BottomNavigationView shift mode
 *
 * Created by tonyliao on 2018/5/27.
 */
object SBottomNavigationHelper {
    @SuppressLint("RestrictedApi")
    fun disableShiftMode(view: BottomNavigationView) {
        if (view.menu.size() > 3) {
            val menuView = view.getChildAt(0) as BottomNavigationMenuView
            try {
                val shiftingMode = menuView.javaClass.getDeclaredField("mShiftingMode")
                shiftingMode.isAccessible = true
                shiftingMode.setBoolean(menuView, false)
                shiftingMode.isAccessible = false
                for (index in 0 until menuView.childCount) {
                    val itemView = menuView.getChildAt(index) as BottomNavigationItemView
                    itemView.setShiftingMode(false)
                    //set once again checked value, so view will be updated
                    itemView.setChecked(itemView.itemData.isChecked)
                }
            } catch (e: NoSuchFieldException) {
                SLog.e(SBottomNavigationHelper.javaClass.simpleName, "Unable to get shift mode field: $e")
            } catch (e: IllegalAccessException) {
                SLog.e(SBottomNavigationHelper.javaClass.simpleName, "Unable to change value of shift mode: $e")
            }
        }
    }
}