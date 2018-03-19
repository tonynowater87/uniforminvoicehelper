package com.tonynowater.uniforminvoicehelper.view.test

import com.tonynowater.uniforminvoicehelper.base.IBaseView
import com.tonynowater.uniforminvoicehelper.base.SBasePresenter
import com.tonynowater.uniforminvoicehelper.base.SBaseRecyclerViewAdapter
import com.tonynowater.uniforminvoicehelper.data.net.SNetRepository
import com.tonynowater.uniforminvoicehelper.util.SBarCodeImageGenerator
import com.tonynowater.uniforminvoicehelper.view.component.SBarCodeView
import javax.inject.Inject

/**
 * Created by tonyliao on 2018/3/15.
 */
class STestPresenter @Inject constructor() : SBasePresenter<STestPresenter.ITestView, SNetRepository>(), SBaseRecyclerViewAdapter.OnClickItemListener<String> {

    fun clickTestButton(content: String) {
        mView?.showBarCode(SBarCodeImageGenerator.generateBarCodeImage(content, mView?.getBarcodeView()!!.width, mView?.getBarcodeView()!!.height))
    }

    override fun onClickItem(position: Int, item: String) {
        mView?.showToast(item)
    }

    interface ITestView : IBaseView {
        fun showBarCode(barCodeItem: SBarCodeImageGenerator.BarCodeItem)
        fun getBarcodeView():SBarCodeView
    }
}