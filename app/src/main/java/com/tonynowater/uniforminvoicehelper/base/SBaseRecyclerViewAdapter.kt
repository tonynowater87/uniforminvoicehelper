package com.tonynowater.uniforminvoicehelper.base

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tonynowater.uniforminvoicehelper.R
import java.util.*

/**
 * Created by tonyliao on 2017/11/17.
 */
abstract class SBaseRecyclerViewAdapter<T> : RecyclerView.Adapter<SBaseRecyclerViewAdapter.BaseViewHolder> {

    interface OnClickItemListener<in T> {
        fun onClickItem(position: Int, t: T)
    }

    protected var m_data: MutableList<T>
    protected var m_context: Context? = null
    protected var m_footerViewIsVisible = false
    private var m_onClickItemListener: OnClickItemListener<T>? = null

    constructor() {
        m_data = mutableListOf()
    }

    constructor(m_onClickItemListener: OnClickItemListener<T>) {
        m_data = ArrayList()
        this.m_onClickItemListener = m_onClickItemListener
    }

    constructor(m_data: MutableList<T>, m_onClickItemListener: OnClickItemListener<T>) {
        this.m_data = m_data
        this.m_onClickItemListener = m_onClickItemListener
    }

    constructor(m_data: MutableList<T>) {
        this.m_data = m_data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        m_context = parent.context
        when (viewType) {
            NORMAL_VIEW_TYPE -> {
                val normalView = LayoutInflater.from(m_context).inflate(layoutResource, parent, false)
                val baseViewHolder = BaseViewHolder(normalView)
                normalView.setOnClickListener {
                    if (m_onClickItemListener != null) {
                        val pos = baseViewHolder.adapterPosition
                        if (pos != -1) {
                            m_onClickItemListener!!.onClickItem(pos, m_data[pos])
                        }
                    }
                }
                return baseViewHolder
            }
            FOOTER_VIEW_TYPE -> {
                val footerView = LayoutInflater.from(m_context).inflate(footerLayoutId, parent, false)
                if (false == m_footerViewIsVisible) {
                    footerView.visibility = View.GONE
                }
                return BaseViewHolder(footerView)
            }
            else -> throw RuntimeException("recycler view adapter wrong viewType")
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        if (getItemViewType(position) == NORMAL_VIEW_TYPE) {
            bindView(holder, position)
        }
    }

    override fun getItemCount(): Int {
        return if (supportFooter && m_data.size > 0 && m_footerViewIsVisible) {
            m_data.size + 1
        } else {
            m_data.size
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (supportFooter && itemCount > 0 && m_footerViewIsVisible && position + 1 == itemCount) {
            FOOTER_VIEW_TYPE
        } else NORMAL_VIEW_TYPE

    }

    protected abstract fun bindView(holder: BaseViewHolder, position: Int)

    protected abstract val layoutResource: Int

    /** @return Footer的Layout
     */
    protected val footerLayoutId: Int get() = R.layout.view_list_view_footer

    /** @return 是否要用FooterView
     */
    protected val supportFooter: Boolean get() = false

    /** @param footviewVisible 設置FootView是否可見
     */
    fun setFootviewVisible(footviewVisible: Boolean) {
        m_footerViewIsVisible = footviewVisible
    }

    fun addData(data: T) {
        m_data.add(data)
        notifyDataSetChanged()
    }

    fun addDatas(datas: List<T>) {
        m_data.addAll(datas)
        notifyDataSetChanged()
    }

    fun removeData(position: Int) {
        m_data.removeAt(position)
        notifyItemRemoved(position)
    }

    fun removeAllData() {
        m_data.clear()
        notifyDataSetChanged()
    }

    fun getData(position: Int): T {
        return m_data[position]
    }

    class BaseViewHolder(val rootView: View) : RecyclerView.ViewHolder(rootView) {
        private val viewCache = SparseArray<View>()

        fun <R> getView(viewID: Int): R? {
            var cachedView: View? = viewCache.get(viewID)
            if (null == cachedView) {
                cachedView = rootView.findViewById(viewID)
                viewCache.put(viewID, cachedView)
            }
            return cachedView as? R
        }
    }

    companion object {

        protected val NORMAL_VIEW_TYPE = 1
        protected val FOOTER_VIEW_TYPE = 2
    }
}