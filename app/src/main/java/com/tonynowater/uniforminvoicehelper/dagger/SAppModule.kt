package com.tonynowater.uniforminvoicehelper.dagger

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by tonyliao on 2018/3/22.
 */
@Module(includes = [SActivityModule::class
                    , SFragmentModule::class
                    , SNetModule::class
                    , SRoomModule::class
                    , AndroidSupportInjectionModule::class])
class SAppModule(var mApplication: Application) {

    @Provides
    @Singleton
    fun provideApplication(): Application = mApplication
}
