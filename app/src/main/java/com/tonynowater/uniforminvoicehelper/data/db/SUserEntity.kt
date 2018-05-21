package com.tonynowater.uniforminvoicehelper.data.db

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull

/**
 * Created by tonyliao on 2018/3/6.
 */
@Entity
data class SUserEntity(@PrimaryKey(autoGenerate = true)
                       @NonNull
                       var id: Long = 0,
                       var account: String,
                       var password: String) {
}