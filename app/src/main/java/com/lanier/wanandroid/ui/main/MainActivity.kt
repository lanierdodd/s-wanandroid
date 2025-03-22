package com.lanier.wanandroid.ui.main

import android.os.Bundle
import android.widget.FrameLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.lanier.wanandroid.R
import com.lanier.wanandroid.logic.utils.FragmentSwitchHelper

class MainActivity : AppCompatActivity() {

    private lateinit var btmNavigation: BottomNavigationView
    private lateinit var frameLayout: FrameLayout

    private val fragmentHelper by lazy {
        FragmentSwitchHelper(R.id.frameLayout, supportFragmentManager)
    }

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

        btmNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_home -> fragmentHelper.switchFirstFraSafely()
                R.id.menu_plaza -> fragmentHelper.switchFra(1)
            }
            true
        }

        fragmentHelper.addFragment(HomeFragment())
        fragmentHelper.addFragment(PlazaFragment())
        fragmentHelper.switchFirstFraSafely()
    }
}