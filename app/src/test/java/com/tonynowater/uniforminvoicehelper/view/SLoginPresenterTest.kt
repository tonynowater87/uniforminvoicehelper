package com.tonynowater.uniforminvoicehelper.view

import com.tonynowater.uniforminvoicehelper.base.IBaseView
import com.tonynowater.uniforminvoicehelper.data.net.SNetRepository
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
 * Created by tonyliao on 2018/3/27.
 */
class SLoginPresenterTest {

    @Mock
    lateinit var view: IBaseView

    @Mock
    lateinit var sNetRepository: SNetRepository

    lateinit var presenter: SLoginPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = SLoginPresenter(sNetRepository)
        presenter.attach(view)
    }

    @After
    fun after() {
        //檢查有沒有少驗證到的動作
        Mockito.verifyNoMoreInteractions(view)
        Mockito.verifyNoMoreInteractions(sNetRepository)
    }

    /**
     * 驗證登入成功的動作
     */
    @Test
    fun testLoginSuccess() {
        val account = "abc"
        val password = "123"
        presenter.login(account, password)
        Mockito.verify(view).showLoading()
        Mockito.verify(sNetRepository).login(account, password, presenter.loginCallback)
        presenter.loginCallback.onSuccess(true)
        Mockito.verify(view).hideLoading()
        Mockito.verify(view).showSuccess()
    }

    /**
     * 驗證登入失敗的動作
     */
    @Test
    fun testLoginFailure() {
        val account = "abc"
        val password = "123"
        val failMsg = "登入失敗"
        presenter.login(account, password)
        Mockito.verify(view).showLoading()
        Mockito.verify(sNetRepository).login(account, password, presenter.loginCallback)
        presenter.loginCallback.onFailure(Throwable(failMsg))
        Mockito.verify(view).hideLoading()
        Mockito.verify(view).showError(failMsg)
    }
}