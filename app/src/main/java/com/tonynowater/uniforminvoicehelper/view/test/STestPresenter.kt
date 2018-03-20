package com.tonynowater.uniforminvoicehelper.view.test

import com.tonynowater.uniforminvoicehelper.base.IBaseView
import com.tonynowater.uniforminvoicehelper.base.SBasePresenter
import com.tonynowater.uniforminvoicehelper.base.SBaseRecyclerViewAdapter
import com.tonynowater.uniforminvoicehelper.data.net.SNetRepository
import com.tonynowater.uniforminvoicehelper.util.SBarCodeImageGenerator
import com.tonynowater.uniforminvoicehelper.util.sp.SP_KEY_ACCOUNT
import com.tonynowater.uniforminvoicehelper.util.sp.SP_KEY_PASSWORD
import com.tonynowater.uniforminvoicehelper.util.sp.SSharePrefUtil
import com.tonynowater.uniforminvoicehelper.view.component.SBarCodeView
import javax.inject.Inject

/**
 * Created by tonyliao on 2018/3/15.
 */
class STestPresenter @Inject constructor() : SBasePresenter<STestPresenter.ITestView, SNetRepository>(), SBaseRecyclerViewAdapter.OnClickItemListener<String> {

    fun clickTestButton(account: String, password: String) {
        SSharePrefUtil.putString(SP_KEY_ACCOUNT, account)
        SSharePrefUtil.putString(SP_KEY_PASSWORD, password)
        mView?.showBarCode(SBarCodeImageGenerator.generateBarCodeImage(account, mView?.getBarcodeView()!!.width, mView?.getBarcodeView()!!.height))
    }

    override fun onClickItem(position: Int, item: String) {
        mView?.showToast(item)
    }

    interface ITestView : IBaseView {
        fun showBarCode(barCodeItem: SBarCodeImageGenerator.BarCodeItem)
        fun getBarcodeView():SBarCodeView
    }
}