package com.tonynowater.uniforminvoicehelper

import android.content.Context
import com.tonynowater.uniforminvoicehelper.dagger.DaggerSAppComponent
import com.tonynowater.uniforminvoicehelper.util.uuid.OpenUDID_manager
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

/**
 * Created by tonyliao on 2018/3/6.
 */
class SApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<SApplication> {
        return DaggerSAppComponent.builder().create(this)
    }

    override fun onCreate() {
        super.onCreate()
        OpenUDID_manager.sync(this)
        context = this
    }

    companion object {
        lateinit var context:Context
    }
}