package com.zhouzhou.newsdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zhou.logutils.Logger

class MainActivity : AppCompatActivity() {

    private val logger = Logger("MainActivity")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);
        logger.d("MainActivity onCreate")
    }
}
