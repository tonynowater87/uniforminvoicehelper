package com.tonynowater.uniforminvoicehelper.dagger

import android.app.Application
import com.tonynowater.uniforminvoicehelper.SApplication
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton

/**
 * Created by tonyliao on 2018/3/6.
 */
@Singleton
@Component(modules = [SAppModule::class])
interface SAppComponent : AndroidInjector<SApplication> {
    fun application():Application
}