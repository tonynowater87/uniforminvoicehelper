package com.tonynowater.uniforminvoicehelper.view.query

import com.tonynowater.uniforminvoicehelper.data.net.SNetRepository
import com.tonynowater.uniforminvoicehelper.data.net.api.SBaseMockJsonData
import com.tonynowater.uniforminvoicehelper.data.net.api.entity.SCarrierInvoiceHeaderEntity
import com.tonynowater.uniforminvoicehelper.util.STimeUtil
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
 * Created by tonyliao on 2018/4/13.
 */
class SCarrierQueryListPresenterTest:SBaseMockJsonData() {

    @Mock
    private lateinit var view: SCarrierQueryListPresenter.ICarrierQueryListView

    @Mock
    private lateinit var sNetRepository: SNetRepository

    private lateinit var presenter: SCarrierQueryListPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = SCarrierQueryListPresenter(sNetRepository)
        presenter.attach(view)
    }

    @After
    fun tearDown() {
        //檢查有沒有少驗證到的動作
        Mockito.verifyNoMoreInteractions(view)
        Mockito.verifyNoMoreInteractions(sNetRepository)
    }

    @Test
    fun queryHeader_1() {
        val dateItem = STimeUtil.DateItem("2018/04/13", "2018/04/14")
        presenter.queryHeader(dateItem)
        Mockito.verify(view).showLoading()
        Mockito.verify(view).clearHeaderData()
        Mockito.verify(view).showDate(dateItem)
        Mockito.verify(view).showQuantity(0, 0)
        Mockito.verify(sNetRepository).getCarrierInvoiceHeader(dateItem.startDate, dateItem.endDate, presenter.callbackHeader)
    }

    @Test
    fun queryHeader_2() {
        presenter.queryHeader(null)
        Mockito.verify(view).showDateLimitPickerDialog()
    }

    @Test
    fun queryDetail() {
        val dto = transferUtil.transferCarrierHeaderEntityToDTO(gson.fromJson(json_carrier_invoice_header_200, SCarrierInvoiceHeaderEntity::class.java))[0]
        presenter.queryDetail(dto)
        Mockito.verify(view).showLoading()
        Mockito.verify(sNetRepository).getCarrierInvoiceDetail(dto.invoiceNo, dto.formatCEDate, presenter.callbackDetail)
    }
}