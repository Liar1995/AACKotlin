package com.sunmeng.aackotlin.ui.base

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import com.sunmeng.aackotlin.ui.listener.ClickListener
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity

/**
 * Created by sunmeng on 2017/12/28.
 * Email:sunmeng995@gmail.com
 * Description:
 */
abstract class BaseActivity<VB : ViewDataBinding> : RxAppCompatActivity(), ClickListener {

    protected lateinit var mBinding: VB

    protected lateinit var mContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, getLayoutId())
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        mContext = this

        initView()

        loadData()
    }

    abstract fun loadData()

    abstract fun initView()

    abstract fun getLayoutId(): Int

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    fun initBackToolbar(toolbar: Toolbar) {
        setSupportActionBar(toolbar)
        val bar = supportActionBar
        if (bar != null) {
            bar.title = null
            bar.setDisplayHomeAsUpEnabled(true)
            bar.setDisplayShowHomeEnabled(true)
            bar.setDisplayShowTitleEnabled(true)
            bar.setHomeButtonEnabled(true)
        }
    }

    override fun onClickListener(v: View?) {
    }
}