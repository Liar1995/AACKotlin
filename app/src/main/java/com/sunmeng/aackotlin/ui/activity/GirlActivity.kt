package com.sunmeng.aackotlin.ui.activity

import android.annotation.SuppressLint
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.BottomSheetDialog
import android.support.v4.view.ViewCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.WindowManager
import com.sunmeng.aackotlin.R
import com.sunmeng.aackotlin.databinding.ActivityGirlDetailsBinding
import com.sunmeng.aackotlin.ui.listener.ItemLongClickPresenter

/**
 * Created by sunmeng on 2017/11/24.
 * Email:sunmeng995@gmail.com
 * Description:
 */
class GirlActivity : AppCompatActivity(), ItemLongClickPresenter<String> {

    private lateinit var dialog: BottomSheetDialog

    override fun onItemLongClick(v: View?, item: String): Boolean {
        dialog.show()
        return false
    }

    private val imageUrl: String by lazy {
        intent.getStringExtra("imageUrl")
    }

    private val imageId: String by lazy {
        intent.getStringExtra("imageId")
    }

    @SuppressLint("InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityGirlBinding = DataBindingUtil.setContentView<ActivityGirlDetailsBinding>(this, R.layout.activity_girl_details)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        activityGirlBinding.presenter = this@GirlActivity
        activityGirlBinding.item = imageUrl
        ViewCompat.setTransitionName(activityGirlBinding.ivGirlDetails, imageId)
        dialog= BottomSheetDialog(this)
        dialog.setContentView(layoutInflater.inflate(R.layout.custom_girl_details_content, null))
    }

}