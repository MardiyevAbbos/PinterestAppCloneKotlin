package com.example.pinterestappkotlin.activity

import android.content.Context
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.pinterestappkotlin.R
import com.example.pinterestappkotlin.fragment.HomeFragment
import com.example.pinterestappkotlin.fragment.ProfileFragment
import com.example.pinterestappkotlin.fragment.SearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.logging.Logger


class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView
    private var isLightStatusBar: Boolean = false
    private lateinit var searchFragment: SearchFragment
    private lateinit var profileFragment: ProfileFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()

    }

    private fun initViews() {
        searchFragment = SearchFragment.newInstance()
        profileFragment = ProfileFragment.newInstance()
        bottomNavigationView = findViewById(R.id.bottomNavigationView)

        replaceFragment(HomeFragment.newInstance())

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> replaceFragment(HomeFragment.newInstance())
                R.id.search -> replaceFragment(searchFragment)
                R.id.profile -> replaceFragment(profileFragment)
            }
            it.isChecked
            return@setOnNavigationItemSelectedListener true
        }

    }

    fun replaceFragment(fragment: Fragment) {
        val backStateName = fragment.javaClass.name
        val manager = supportFragmentManager
        val fragmentPopped = manager.popBackStackImmediate(backStateName, 0)
        if (!fragmentPopped) {
            val ft = manager.beginTransaction()
            ft.replace(R.id.fl_forFragment, fragment)
            ft.addToBackStack(backStateName)
            ft.commit()
        }

    }


    fun bnvVisibility() {
        bottomNavigationView.visibility = View.VISIBLE
    }

    fun bnvHide() {
        bottomNavigationView.visibility = View.GONE
    }


    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1) {
            finish()
        } else if (supportFragmentManager.fragments.any { it is SearchFragment }) {
            if (searchFragment.onBackPressed()) {
                finish()
            }
        }else {
            super.onBackPressed()
        }
    }


    fun clearLightStatusBar() {
        if (!isLightStatusBar && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            var flags: Int = this.window.decorView.systemUiVisibility
//            flags = flags xor View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR  // use XOR here for remove LIGHT_STATUS_BAR from flags
//            this.window.decorView.systemUiVisibility = flags
//            this.window.statusBarColor = ContextCompat.getColor(this, R.color.black)
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.statusBarColor = Color.TRANSPARENT
            }
            isLightStatusBar = true
        }

    }

    fun setLightStatusBar() {
        if (isLightStatusBar && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.statusBarColor = Color.WHITE
            }

            var flags: Int = this.window.decorView.systemUiVisibility
            flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR // add LIGHT_STATUS_BAR to flag
            this.window.decorView.systemUiVisibility = flags
            this.window.statusBarColor = ContextCompat.getColor(this, R.color.white)
            isLightStatusBar = false

        }
    }

    fun closeKeyBoard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

}
