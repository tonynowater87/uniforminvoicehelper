package com.tonynowater.uniforminvoicehelper.view.query

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.afollestad.materialdialogs.MaterialDialog
import com.tonynowater.datelimitpickerdialog.SDateLimitPickerDialog
import com.tonynowater.uniforminvoicehelper.R
import com.tonynowater.uniforminvoicehelper.SApplication
import com.tonynowater.uniforminvoicehelper.base.SBaseFragment
import com.tonynowater.uniforminvoicehelper.base.SBaseRecyclerViewAdapter
import com.tonynowater.uniforminvoicehelper.data.net.api.dto.SCarrierInvoiceDetailDTO
import com.tonynowater.uniforminvoicehelper.data.net.api.dto.SCarrierInvoiceHeaderDTO
import com.tonynowater.uniforminvoicehelper.util.SLog
import com.tonynowater.uniforminvoicehelper.util.STimeUtil
import kotlinx.android.synthetic.main.fragment_carrier_query_list.*


/**
 * Created by tonyliao on 2018/4/10.
 */
class SCarrierQueryListFragment : SBaseFragment<SCarrierQueryListPresenter>(), SCarrierQueryListPresenter.ICarrierQueryListView, SBaseRecyclerViewAdapter.OnClickItemListener<SCarrierInvoiceHeaderDTO> {

    companion object {
        private const val BUNDLE_KEY_TYPE = "BUNDLE_KEY_TYPE"

        fun newInstance(carrierQueryType: ECarrierQueryType): SCarrierQueryListFragment {
            val fragment = SCarrierQueryListFragment()
            val bundle = Bundle()
            bundle.putSerializable(BUNDLE_KEY_TYPE, carrierQueryType)
            fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var mAdapter: SCarrierQueryListAdapter
    private var mIsVisibleToUser = false
    private var mInit = false

    override fun getLayoutId(): Int = R.layout.fragment_carrier_query_list

    override fun initView() {
        SLog.d("${arguments!![BUNDLE_KEY_TYPE]} initView", "test")
        mPresenter.attach(this)
        mAdapter = SCarrierQueryListAdapter(this)
        recycler_view.layoutManager = LinearLayoutManager(SApplication.mInstance)
        recycler_view.adapter = mAdapter
        if (mIsVisibleToUser && !mInit) {
            mPresenter.queryHeader((arguments!![BUNDLE_KEY_TYPE] as ECarrierQueryType).getDateItem())
        }
        mInit = true
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        mIsVisibleToUser = isVisibleToUser
        SLog.d("${arguments!![BUNDLE_KEY_TYPE]} setUserVisibleHint:$mIsVisibleToUser", "test")
        if (mInit && mIsVisibleToUser) {
            mPresenter.queryHeader((arguments!![BUNDLE_KEY_TYPE] as ECarrierQueryType).getDateItem())
        }
    }

    override fun showHeader(dto: List<SCarrierInvoiceHeaderDTO>) {
        mAdapter.addDatas(dto)
    }

    override fun showDetail(dto: List<SCarrierInvoiceDetailDTO>) {
        MaterialDialog.Builder(activity!!)
                .title("發票明細")
                .items(dto)
                .show()
    }

    override fun onClickItem(position: Int, t: SCarrierInvoiceHeaderDTO) {
        mPresenter.queryDetail(t)
    }

    override fun showDate(dateItem: STimeUtil.DateItem?) {
        tv_date_term.text = "${dateItem?.startDate} ~ ${dateItem?.endDate}"
    }

    override fun showQuantity(quantity: Int, sum: Int) {
        tv_quantity.text = "$quantity 筆 共 $sum 元"
    }

    override fun showDateLimitPickerDialog() {
        SDateLimitPickerDialog.SDateLimitPickerDialogBuilder(fragmentManager!!)
                .addListener(object : SDateLimitPickerDialog.OnDatePickerSelectListener {
                    override fun onDatePicked(dateItem: SDateLimitPickerDialog.DateItem) {
                        mPresenter.queryHeader(STimeUtil.DateItem(dateItem.startDate, dateItem.endDate))
                    }
                })
                .show()
    }
}