package com.tonynowater.uniforminvoicehelper.dagger

import android.app.Application
import android.arch.lifecycle.ViewModelProvider
import android.arch.persistence.room.Room
import com.tonynowater.uniforminvoicehelper.data.db.SCustomViewModuleFactory
import com.tonynowater.uniforminvoicehelper.data.db.SUserItemDatabase
import com.tonynowater.uniforminvoicehelper.data.db.SUserItemRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by tonyliao on 2018/3/6.
 */
@Module
class SRoomModule {
    var database: SUserItemDatabase? = null

    constructor(application: Application) {
        this.database = Room.databaseBuilder(application
                , SUserItemDatabase::class.java
                , SUserItemDatabase::javaClass.name).build()
    }

    @Provides
    @Singleton
    fun provideUserItemDao(database: SUserItemDatabase) = database.userItemDao()


    @Provides
    @Singleton
    fun provideUserItemDatabase(application: Application) = database

    @Provides
    @Singleton
    fun provideFactory(repository: SUserItemRepository): ViewModelProvider.Factory {
        return SCustomViewModuleFactory(repository)
    }
}