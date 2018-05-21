package com.tonynowater.uniforminvoicehelper.dagger

import android.app.Application
import android.arch.lifecycle.ViewModelProvider
import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Room
import android.arch.persistence.room.migration.Migration
import android.util.Log
import com.tonynowater.uniforminvoicehelper.data.db.SCustomViewModuleFactory
import com.tonynowater.uniforminvoicehelper.data.db.SUserItemDao
import com.tonynowater.uniforminvoicehelper.data.db.SUserItemDatabase
import com.tonynowater.uniforminvoicehelper.data.db.SUserItemRepository
import com.tonynowater.uniforminvoicehelper.util.sp.LOG_TAG
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
        this.database = Room.databaseBuilder(application
                , SUserItemDatabase::class.java
                , SUserItemDatabase::javaClass.name)
                .addMigrations(migration1To2)
                .build()

        //db's data is only in memory
//        this.database = Room.inMemoryDatabaseBuilder(application
//                , SUserItemDatabase::class.java)
//                .allowMainThreadQueries()//Disables the main thread query check for Room.
//                .build()
    }

    //版本變更 1 -> 2
    private val migration1To2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            Log.d(LOG_TAG, "migrate 1 to 2 (version:${database.version}, path:${database.path})")
            database.execSQL("ALTER TABLE User ADD COLUMN birth TEXT")
        }
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