package com.tonynowater.uniforminvoicehelper.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

/**
 * Created by tonyliao on 2018/3/6.
 */
@Database(entities = [(UserItem::class)], version = 1)
abstract class UserItemDatabase: RoomDatabase() {
    abstract fun userItemDao(): UserItemDao
}