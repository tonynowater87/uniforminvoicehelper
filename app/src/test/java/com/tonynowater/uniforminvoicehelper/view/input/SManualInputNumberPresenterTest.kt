package com.tonynowater.uniforminvoicehelper.view.input

import com.tonynowater.uniforminvoicehelper.data.net.SNetRepository
import org.junit.After
import org.junit.Test

import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
 * Created by tonyliao on 2018/6/3.
 */
class SManualInputNumberPresenterTest {

    @Mock
    private lateinit var view: SManualInputNumberPresenter.IManualInputNumberView

    @Mock
    private lateinit var module: SNetRepository

    private lateinit var presenter: SManualInputNumberPresenter

    @Before
    fun setUp() {
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
        Mockito.verify(module).getPrizeNumberList(presenter.callbackQueryPrizeList)
    }

    @Test
    fun afterTextChanged_01() {
        val after = "1"
        val before = "2"
        presenter.afterTextChanged(before, after)
        Mockito.verify(view).setInput(before + after)
    }
}