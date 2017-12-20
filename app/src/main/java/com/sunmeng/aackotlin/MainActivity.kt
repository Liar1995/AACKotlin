package com.sunmeng.aackotlin

import android.annotation.SuppressLint
import android.databinding.DataBindingUtil.setContentView
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.FrameLayout
import com.sunmeng.aackotlin.ui.fragment.GirlListFragment

class MainActivity : AppCompatActivity() {

    private var pinal: FrameLayout? = null
    private var mFragmentManager: FragmentManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    @SuppressLint("CommitTransaction")
    private fun initView() {
        pinal = findViewById(R.id.fram_context)
        mFragmentManager=supportFragmentManager
        val mGirlListFragment=GirlListFragment()
        mFragmentManager?.beginTransaction()?.add(R.id.fram_context,mGirlListFragment)?.commit()

    }
}
