package com.tonynowater.uniforminvoicehelper.data.db

import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE

/**
 * Created by tonyliao on 2018/3/6.
 */
@Dao
interface SUserItemDao {
    @Query("SELECT * FROM User WHERE id = :id")
    fun queryUserById(id: Long): SUserEntity

    @Query("SELECT * FROM User")
    fun queryAll(): List<SUserEntity>

    @Insert(onConflict = REPLACE)
    fun insertUser(entity: SUserEntity): Long

    @Delete
    fun deleteUser(userEntity: SUserEntity)

    @Query("DELETE FROM User")
    fun deleteAll()

    @Query("UPDATE User SET account = :account WHERE id = :id")
    fun updateUser(id: Long, account: String)
}