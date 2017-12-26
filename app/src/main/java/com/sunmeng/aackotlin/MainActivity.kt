package com.sunmeng.aackotlin

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import android.view.WindowManager
import android.widget.FrameLayout
import com.sunmeng.aackotlin.ui.fragment.GirlListFragment
import android.support.design.widget.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_toolbar.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    private var mFragmentManager: FragmentManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        initView()
    }

    @SuppressLint("CommitTransaction")
    private fun initView() {
        mFragmentManager = supportFragmentManager
        val mGirlListFragment = GirlListFragment()
        mFragmentManager?.beginTransaction()?.add(R.id.fram_context, mGirlListFragment)?.commit()
        txt_home_title.setDuration(3*1000)
        txt_home_title.show()
        navigation_view.setNavigationItemSelectedListener(this)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        item.isChecked = true
        Log.i("Summer","xxxx")
        return true
    }
}
