package com.sunmeng.aackotlin.ui.binding

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.github.chrisbanes.photoview.PhotoView
import com.sunmeng.aackotlin.utils.GlideUtil

/**
 * Created by sunmeng on 26/12/2017.
 * Email:sunmeng995@gmail.com
 * Description:
 */

@BindingAdapter(value = "imageUri")
fun bindUrl(imageView: ImageView, url: String?) {
    GlideUtil.displayImage(url!!, imageView, null)
}

@BindingAdapter(value = "photoViewImageUri")
fun bindUrlByPhotoView(imageView: PhotoView, url: String?) {
    GlideUtil.displayPhotoViewImage(url!!, imageView, null)
}