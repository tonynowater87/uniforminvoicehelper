package com.tonynowater.uniforminvoicehelper.view.input

import com.tonynowater.uniforminvoicehelper.SBaseRobolectricTestCase
import com.tonynowater.uniforminvoicehelper.data.net.SNetRepository
import org.junit.After
import org.junit.Test

import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner

/**
 * Created by tonyliao on 2018/6/3.
 */
@RunWith(RobolectricTestRunner::class)
class SManualInputNumberPresenterTest: SBaseRobolectricTestCase() {

    @Mock
    private lateinit var view: SManualInputNumberPresenter.IManualInputNumberView

    @Mock
    private lateinit var module: SNetRepository

    private lateinit var presenter: SManualInputNumberPresenter

    @Before
    override fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = SManualInputNumberPresenter(module)
        presenter.attach(view)
    }

    @After
    fun after() {
        //檢查有沒有少驗證到的動作
        Mockito.verifyNoMoreInteractions(view)
        Mockito.verifyNoMoreInteractions(module)
    }

    @Test
    fun clickClearButton() {
        presenter.clickClearButton()
        Mockito.verify(view).removeTextWatcher()
        Mockito.verify(view).setInput("")
        Mockito.verify(view).addTextWatcher()
    }

    @Test
    fun queryPrizeList() {
        presenter.queryPrizeList()
        Mockito.verify(module).getPrizeNumberList(callbackNet = presenter.callbackQueryPrizeList)
    }

    @Test
    fun afterTextChanged_01() {
        val after = "1"
        val before = "2"
        presenter.afterTextChanged(before, after)
        Mockito.verify(view).setInput(before + after)
    }

    @Test
    fun afterTextChanged_02() {
        val after = "3"
        val before = "12"
        presenter.afterTextChanged(before, after)
        Mockito.verify(view).setInput("沒中獎:$before$after")
        Mockito.verify(view).showWinningDetail("1張,中0張")
    }

    @Test
    fun afterTextChanged_03() {
        val after = "3"
        val before = "12"
        presenter.afterTextChanged(before, after)
        Mockito.verify(view).setInput("沒中獎:$before$after")
        Mockito.verify(view).showWinningDetail("2張,中0張")
    }
}