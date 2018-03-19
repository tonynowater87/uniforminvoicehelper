package com.tonynowater.uniforminvoicehelper.base

import com.tonynowater.uniforminvoicehelper.data.net.SNetRepositoy
import javax.inject.Inject

/**
 * Created by tonyliao on 2018/3/15.
 */
open class SBasePresenter <V: IBaseView, M: SNetRepositoy> {

    var mView: V? = null

    @Inject
    lateinit var mModule: M

    fun attach(mView: V) {
        this.mView = mView
    }

    fun detach() {
        mView = null
    }
}