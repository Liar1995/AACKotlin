package com.sunmeng.aackotlin.ui.listener

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.request.RequestOptions
import com.github.chrisbanes.photoview.PhotoView

/**
 * Created by sunmeng on 27/12/2017.
 * Email:sunmeng995@gmail.com
 * Description:
 */
interface ImageLoader {

    fun displayImage(url: String, imageView: ImageView, options: RequestOptions?)

    fun displayPhotoViewImage( url: String, imageView: PhotoView, options: RequestOptions?)

}