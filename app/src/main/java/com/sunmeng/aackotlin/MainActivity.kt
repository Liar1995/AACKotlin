package com.sunmeng.aackotlin

import android.annotation.SuppressLint
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.view.ViewPager
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.FrameLayout
import com.sunmeng.aackotlin.ui.activity.BaseActivity
import com.sunmeng.aackotlin.ui.fragment.GirlListFragment

class MainActivity : BaseActivity() {

    private var pinal: FrameLayout? = null
    private var toolbar: Toolbar? = null
    private var mFragmentManager: FragmentManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    @SuppressLint("CommitTransaction")
    private fun initView() {
        pinal = findViewById(R.id.fram_context)
        toolbar = findViewById(R.id.tool_bar)
        initToolbar(toolbar!!, false, R.string.app_name)
        mFragmentManager=supportFragmentManager
        val mGirlListFragment=GirlListFragment()
        mFragmentManager?.beginTransaction()?.add(R.id.fram_context,mGirlListFragment)?.commit()
    }
}
