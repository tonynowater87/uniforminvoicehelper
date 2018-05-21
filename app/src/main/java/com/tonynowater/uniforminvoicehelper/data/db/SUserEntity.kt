package com.tonynowater.uniforminvoicehelper.data.db

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by tonyliao on 2018/3/6.
 */
@Entity(tableName = "User")//指定自訂的TableName，預設為ClassName
data class SUserEntity(@PrimaryKey(autoGenerate = true) var id: Long = 0,//must has a primaryKey
                       @ColumnInfo(name = "account") var account: String,
                       @ColumnInfo(name = "password") var password: String,
                       @ColumnInfo(name = "birth") var birth: String? = null)//new column added by version 2
