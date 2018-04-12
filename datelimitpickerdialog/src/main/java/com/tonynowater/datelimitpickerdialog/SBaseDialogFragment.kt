package com.tonynowater.datelimitpickerdialog

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window

/**
 * Created by tonyliao on 2018/4/10.
 */
abstract class SBaseDialogFragment: DialogFragment() {

    companion object {
        private const val BUNDLE_IS_RECYCLE = "BUNDLE_IS_RECYCLE"
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (savedInstanceState != null) {
            if (savedInstanceState.getBoolean(BUNDLE_IS_RECYCLE)) {
                dismiss()
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        return inflater.inflate(getLayoutId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(SUiScaleUtil.getInstance(view.context))
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(BUNDLE_IS_RECYCLE, true)
    }

    abstract fun getLayoutId(): Int

    abstract fun initView(uiScaleUtil: SUiScaleUtil)
}