package com.tonynowater.uniforminvoicehelper.view.query

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.tonynowater.uniforminvoicehelper.R
import com.tonynowater.uniforminvoicehelper.SApplication
import com.tonynowater.uniforminvoicehelper.base.SBaseFragment
import com.tonynowater.uniforminvoicehelper.base.SBaseRecyclerViewAdapter
import com.tonynowater.uniforminvoicehelper.data.net.api.dto.SCarrierInvoiceDetailDTO
import com.tonynowater.uniforminvoicehelper.data.net.api.dto.SCarrierInvoiceHeaderDTO
import kotlinx.android.synthetic.main.fragment_carrier_query_list.*
import com.afollestad.materialdialogs.MaterialDialog



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

    override fun getLayoutId(): Int = R.layout.fragment_carrier_query_list

    override fun initView() {
        mPresenter.attach(this)
        mAdapter = SCarrierQueryListAdapter(this)
        recycler_view.layoutManager = LinearLayoutManager(SApplication.mInstance)
        recycler_view.adapter = mAdapter
        mPresenter.queryHeader((arguments!![BUNDLE_KEY_TYPE] as ECarrierQueryType))
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
}