package com.lanier.wanandroid.ui

import android.os.Bundle
import android.widget.FrameLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.lanier.wanandroid.R

class MainActivity : AppCompatActivity() {

    private lateinit var btmNavigation: BottomNavigationView
    private lateinit var frameLayout: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }

        btmNavigation = findViewById(R.id.btmNav)
        frameLayout = findViewById(R.id.frameLayout)

        supportFragmentManager.beginTransaction()
            .add(frameLayout.id, HomeFragment(), "HomeFragment")
            .commit()
    }
}