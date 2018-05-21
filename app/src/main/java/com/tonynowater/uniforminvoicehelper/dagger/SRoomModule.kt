package com.tonynowater.uniforminvoicehelper.dagger

import android.app.Application
import android.arch.lifecycle.ViewModelProvider
import android.arch.persistence.room.Room
import com.tonynowater.uniforminvoicehelper.data.db.SCustomViewModuleFactory
import com.tonynowater.uniforminvoicehelper.data.db.SUserItemDao
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
    private var database: SUserItemDatabase

    constructor(application: Application) {
//        this.database = Room.databaseBuilder(application
//                , SUserItemDatabase::class.java
//                , SUserItemDatabase::javaClass.name)
//                //.allowMainThreadQueries()//Disables the main thread query check for Room.
//                .build()

        //db's data is only in memory
        this.database = Room.inMemoryDatabaseBuilder(application
                , SUserItemDatabase::class.java)
                //.allowMainThreadQueries()//Disables the main thread query check for Room.
                .build()
    }

    @Provides
    @Singleton
    fun provideUserItemDao(database: SUserItemDatabase): SUserItemDao = database.userItemDao()

    @Provides
    @Singleton
    fun provideUserItemDatabase(application: Application):SUserItemDatabase = database

    @Provides
    @Singleton
    fun provideUserItemRepository(userItemDao: SUserItemDao):SUserItemRepository = SUserItemRepository(userItemDao)

    @Provides
    @Singleton
    fun provideFactory(repository: SUserItemRepository): ViewModelProvider.Factory {
        return SCustomViewModuleFactory(repository)
    }
}