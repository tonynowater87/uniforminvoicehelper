package com.tonynowater.uniforminvoicehelper.base

import javax.inject.Inject

/**
 * Created by tonyliao on 2018/3/15.
 */
open class SBasePresenter<V: IBaseView, M> @Inject constructor(var mModule: M) {

    var mView: V? = null

    fun attach(mView: V) {
        this.mView = mView
    }

    fun detach() {
        mView = null
    }
}