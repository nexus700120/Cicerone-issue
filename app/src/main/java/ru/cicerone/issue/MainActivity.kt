package ru.cicerone.issue

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(android.R.id.content, RootFragment())
            .commitAllowingStateLoss()
    }
}