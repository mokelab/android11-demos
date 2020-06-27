package com.mokelab.demo.android11.ime

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class WindowInsetsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_window_insets)

        window.setDecorFitsSystemWindows(false)
    }
}