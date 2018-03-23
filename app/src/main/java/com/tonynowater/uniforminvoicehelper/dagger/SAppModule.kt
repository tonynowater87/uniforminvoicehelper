package com.tonynowater.uniforminvoicehelper.dagger

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by tonyliao on 2018/3/22.
 */
@Module
class SAppModule(var mApplication: Application) {

    @Provides
    @Singleton
    fun provideApplication():Application = mApplication
}
