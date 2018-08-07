package com.tonynowater.uniforminvoicehelper.data.net

import com.tonynowater.uniforminvoicehelper.TestSApplication
import com.tonynowater.uniforminvoicehelper.data.net.api.ICarrierApi
import com.tonynowater.uniforminvoicehelper.data.net.api.IInvAppApi
import com.tonynowater.uniforminvoicehelper.data.net.api.dto.SCarrierInvoiceDetailDTO
import com.tonynowater.uniforminvoicehelper.data.net.api.dto.SCarrierInvoiceHeaderDTO
import com.tonynowater.uniforminvoicehelper.data.net.api.dto.SInvAppPrizeNumListDTO
import com.tonynowater.uniforminvoicehelper.data.net.api.entity.SCarrierInvoiceDetailEntity
import com.tonynowater.uniforminvoicehelper.data.net.api.entity.SCarrierInvoiceHeaderEntity
import com.tonynowater.uniforminvoicehelper.data.net.api.entity.SInvAppPrizeNumListEntity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.inOrder
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * Created by tonyliao on 2018/3/28.
 */
@RunWith(RobolectricTestRunner::class)
@Config(application = TestSApplication::class)
class SNetRepositoryTest {

    @Mock
    lateinit var iInvAppApi: IInvAppApi

    @Mock
    lateinit var iInvAppApiObservable:Observable<SInvAppPrizeNumListEntity>

    @Mock
    lateinit var iInvAppApiCallback: IOnNetQueryCallback<SInvAppPrizeNumListDTO>

    @Mock
    lateinit var iCarrierAPi: ICarrierApi

    @Mock
    lateinit var iCarrierInvoiceHeaderObservable: Observable<SCarrierInvoiceHeaderEntity>

    @Mock
    lateinit var iCarrierInvoiceHeaderCallback: IOnNetQueryCallback<MutableList<SCarrierInvoiceHeaderDTO>>

    @Mock
    lateinit var iCarrierInvoiceDetailObservable: Observable<SCarrierInvoiceDetailEntity>

    @Mock
    lateinit var iCarrierInvoiceDetailCallback: IOnNetQueryCallback<MutableList<SCarrierInvoiceDetailDTO>>

    @Mock
    lateinit var loginCallback: IOnNetQueryCallback<Any>

    lateinit var repository: SNetRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        repository = SNetRepository(iInvAppApi, iCarrierAPi)
    }

    @Test
    fun getPrizeNumberList() {

        Mockito.`when`(iInvAppApi.getPrizeNumberList(invTerm = "10706"))
                .thenReturn(iInvAppApiObservable)

        Mockito.`when`(iInvAppApiObservable.subscribeOn(Schedulers.io()))
                .thenReturn(iInvAppApiObservable)

        Mockito.`when`(iInvAppApiObservable.observeOn(AndroidSchedulers.mainThread()))
                .thenReturn(iInvAppApiObservable)

        repository.getPrizeNumberList(callbackNet = iInvAppApiCallback)

        val inOrder = inOrder(iInvAppApi, iInvAppApiObservable)
        inOrder.verify(iInvAppApi).getPrizeNumberList(invTerm = "10706")
        inOrder.verify(iInvAppApiObservable).subscribeOn(Schedulers.io())
        inOrder.verify(iInvAppApiObservable).observeOn(AndroidSchedulers.mainThread())
        inOrder.verify(iInvAppApiObservable).subscribe(Mockito.any(), Mockito.any())
    }

    @Test
    fun getCarrierInvoiceDetail() {
        val invNum = "1111"
        val invDate = "2018/03/29"

        Mockito.`when`(iCarrierAPi.getCarrierInvoiceDetail(invNum = invNum, invDate = invDate))
                .thenReturn(iCarrierInvoiceDetailObservable)

        Mockito.`when`(iCarrierInvoiceDetailObservable.subscribeOn(Schedulers.io()))
                .thenReturn(iCarrierInvoiceDetailObservable)

        Mockito.`when`(iCarrierInvoiceDetailObservable.observeOn(AndroidSchedulers.mainThread()))
                .thenReturn(iCarrierInvoiceDetailObservable)

        repository.getCarrierInvoiceDetail(invNum, invDate, iCarrierInvoiceDetailCallback)

        val inOrder = inOrder(iCarrierAPi, iCarrierInvoiceDetailObservable)
        inOrder.verify(iCarrierAPi).getCarrierInvoiceDetail(invNum = invNum, invDate = invDate)
        inOrder.verify(iCarrierInvoiceDetailObservable).subscribeOn(Schedulers.io())
        inOrder.verify(iCarrierInvoiceDetailObservable).observeOn(AndroidSchedulers.mainThread())
        inOrder.verify(iCarrierInvoiceDetailObservable).subscribe(Mockito.any(), Mockito.any())
    }

    @Test
    fun getCarrierInvoiceHeader_1() {
        val startDate = "2018/04/13"
        val endDate = "2018/04/14"

        Mockito.`when`(iCarrierAPi.getCarrierInvoiceHeader(startDate = startDate, endDate = endDate, onlyWinningInv = "Y"))
                .thenReturn(iCarrierInvoiceHeaderObservable)

        Mockito.`when`(iCarrierInvoiceHeaderObservable.subscribeOn(Schedulers.io()))
                .thenReturn(iCarrierInvoiceHeaderObservable)

        Mockito.`when`(iCarrierInvoiceHeaderObservable.observeOn(AndroidSchedulers.mainThread()))
                .thenReturn(iCarrierInvoiceHeaderObservable)

        repository.getCarrierInvoiceHeader(startDate, endDate, true, iCarrierInvoiceHeaderCallback)

        val inOrder = inOrder(iCarrierAPi, iCarrierInvoiceHeaderObservable)
        inOrder.verify(iCarrierAPi).getCarrierInvoiceHeader(startDate = startDate, endDate = endDate, onlyWinningInv = "Y")
        inOrder.verify(iCarrierInvoiceHeaderObservable).subscribeOn(Schedulers.io())
        inOrder.verify(iCarrierInvoiceHeaderObservable).observeOn(AndroidSchedulers.mainThread())
        inOrder.verify(iCarrierInvoiceHeaderObservable).subscribe(Mockito.any(), Mockito.any())
    }

    @Test
    fun getCarrierInvoiceHeader_2() {
        val startDate = "2018/04/13"
        val endDate = "2018/04/14"

        Mockito.`when`(iCarrierAPi.getCarrierInvoiceHeader(startDate = startDate, endDate = endDate, onlyWinningInv = "N"))
                .thenReturn(iCarrierInvoiceHeaderObservable)

        Mockito.`when`(iCarrierInvoiceHeaderObservable.subscribeOn(Schedulers.io()))
                .thenReturn(iCarrierInvoiceHeaderObservable)

        Mockito.`when`(iCarrierInvoiceHeaderObservable.observeOn(AndroidSchedulers.mainThread()))
                .thenReturn(iCarrierInvoiceHeaderObservable)

        repository.getCarrierInvoiceHeader(startDate, endDate, false, iCarrierInvoiceHeaderCallback)

        val inOrder = inOrder(iCarrierAPi, iCarrierInvoiceHeaderObservable)
        inOrder.verify(iCarrierAPi).getCarrierInvoiceHeader(startDate = startDate, endDate = endDate, onlyWinningInv = "N")
        inOrder.verify(iCarrierInvoiceHeaderObservable).subscribeOn(Schedulers.io())
        inOrder.verify(iCarrierInvoiceHeaderObservable).observeOn(AndroidSchedulers.mainThread())
        inOrder.verify(iCarrierInvoiceHeaderObservable).subscribe(Mockito.any(), Mockito.any())
    }

    @Test
    fun login() {
        val cardNo = "Abc"
        val cardEncrypt = "123"

        Mockito.`when`(iCarrierAPi.getCarrierInvoiceHeader(cardNo = cardNo, cardEncrypt = cardEncrypt))
                .thenReturn(iCarrierInvoiceHeaderObservable)

        Mockito.`when`(iCarrierInvoiceHeaderObservable.subscribeOn(Schedulers.io()))
                .thenReturn(iCarrierInvoiceHeaderObservable)

        Mockito.`when`(iCarrierInvoiceHeaderObservable.observeOn(AndroidSchedulers.mainThread()))
                .thenReturn(iCarrierInvoiceHeaderObservable)

        repository.login(cardNo, cardEncrypt, loginCallback)

        val inOrder = inOrder(iCarrierAPi, iCarrierInvoiceHeaderObservable)
        inOrder.verify(iCarrierAPi).getCarrierInvoiceHeader(cardNo = cardNo, cardEncrypt = cardEncrypt)
        inOrder.verify(iCarrierInvoiceHeaderObservable).subscribeOn(Schedulers.io())
        inOrder.verify(iCarrierInvoiceHeaderObservable).observeOn(AndroidSchedulers.mainThread())
        inOrder.verify(iCarrierInvoiceHeaderObservable).subscribe(Mockito.any(), Mockito.any())
    }
}