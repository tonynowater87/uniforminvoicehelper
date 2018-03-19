package com.tonynowater.uniforminvoicehelper.data.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query

/**
 * Created by tonyliao on 2018/3/6.
 */
@Dao
interface UserItemDao {
    @Query("SELECT * FROM UserItem WHERE id = :id")
    fun getUserItemById(id: String): LiveData<UserItem>

    @Insert(onConflict = REPLACE)
    fun insertUserItem(item: UserItem): Long

    @Delete
    fun deleteAll(userItem: UserItem)
}