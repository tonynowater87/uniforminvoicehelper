package com.tonynowater.uniforminvoicehelper.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.tonynowater.uniforminvoicehelper.R
import dagger.android.support.DaggerAppCompatActivity

class SMainActivity : DaggerAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, SMainActivity::class.java))
        }
    }
}
