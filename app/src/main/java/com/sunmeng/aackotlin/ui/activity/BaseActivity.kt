package com.sunmeng.aackotlin.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import com.sunmeng.aackotlin.R

/**
 * Created by sunmeng on 2017/11/23.
 * Email:sunmeng995@gmail.com
 * Description:
 */
@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity(), Toolbar.OnMenuItemClickListener {

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (this is AboutActivity) {
            return super.onCreateOptionsMenu(menu)
        }
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onMenuItemClick(p0: MenuItem?): Boolean {
        when (p0?.itemId) {
            R.id.menu_main_about -> startActivity(Intent(this,AboutActivity::class.java))
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){

        }
        return true
    }

    fun initToolbar(toolbar:Toolbar,setDisplayHomeAsUpEnabled:Boolean,resId:Int){
        initToolbar(toolbar, setDisplayHomeAsUpEnabled, getString(resId))
    }

    fun initToolbar(toolbar:Toolbar,displayHomeAsUpEnabled:Boolean,title:String){
        setSupportActionBar(toolbar)
        toolbar.setOnMenuItemClickListener(this)
        if (supportActionBar!=null){
            supportActionBar!!.setDisplayHomeAsUpEnabled(displayHomeAsUpEnabled)
            supportActionBar!!.title = title
        }

    }

}