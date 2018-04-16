package com.tonynowater.uniforminvoicehelper.view.query

import com.tonynowater.uniforminvoicehelper.util.STimeUtil
import java.io.Serializable

/**
 * Created by tonyliao on 2018/4/16.
 */
data class SCarrierQueryDateData(val type: ECarrierQueryType
                                 , var dateItem: STimeUtil.DateItem) : Serializable {
}