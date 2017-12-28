package com.sunmeng.aackotlin

import android.annotation.SuppressLint
import android.databinding.DataBindingUtil.setContentView
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import android.view.WindowManager
import android.widget.FrameLayout
import com.sunmeng.aackotlin.ui.fragment.GirlListFragment
import android.support.design.widget.NavigationView
import com.irozon.sneaker.Sneaker
import com.sunmeng.aackotlin.databinding.ActivityMainBinding
import com.sunmeng.aackotlin.ui.base.BaseActivity
import com.sunmeng.aackotlin.ui.showSneaker
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_toolbar.*


class MainActivity : BaseActivity<ActivityMainBinding>(), NavigationView.OnNavigationItemSelectedListener {

    override fun initView() {
        txt_home_title.setDuration(3 * 1000)
        txt_home_title.show()
    }

    override fun getLayoutId(): Int = R.layout.activity_main

    private var mFragmentManager: FragmentManager? = null

    override fun loadData() {
        mFragmentManager = supportFragmentManager
        val mGirlListFragment = GirlListFragment()
        mFragmentManager?.beginTransaction()?.add(R.id.fram_context, mGirlListFragment)?.commit()
        navigation_view.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        item.isChecked = true
        Log.i("Summer", "xxxx")
        return true
    }
}
