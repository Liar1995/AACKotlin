package com.sunmeng.aackotlin.ui.binding

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * Created by sunmeng on 26/12/2017.
 * Email:sunmeng995@gmail.com
 * Description:
 */
@BindingAdapter(value = "imageUri")
fun bindUrl(imageView: ImageView, url: String?){
    Glide.with(imageView).load(url).into(imageView)
}