package com.tonynowater.uniforminvoicehelper.data.db

import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE

/**
 * Created by tonyliao on 2018/3/6.
 */
@Dao
interface SUserItemDao {
    @Query("SELECT * FROM SUserEntity WHERE id = :id")
    fun getUserItemById(id: Long): SUserEntity

    @Query("SELECT * FROM SUserEntity")
    fun queryAllItems():List<SUserEntity>

    @Insert(onConflict = REPLACE)
    fun insertUserItem(entity: SUserEntity): Long

    @Delete
    fun deleteUserItem(userEntity: SUserEntity)
}