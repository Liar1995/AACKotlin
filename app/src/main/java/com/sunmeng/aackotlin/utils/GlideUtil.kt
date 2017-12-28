package com.sunmeng.aackotlin.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestOptions
import com.github.chrisbanes.photoview.PhotoView
import com.sunmeng.aackotlin.ui.listener.ImageLoader


/**
 * Created by sunmeng on 27/12/2017.
 * Email:sunmeng995@gmail.com
 * Description:
 */
object GlideUtil : ImageLoader {

    override fun displayImage(url: String, imageView: ImageView, options: RequestOptions?) {
        if (options == null)
            Glide.with(imageView).load(url).transition(withCrossFade()).into(imageView)
        else
            Glide.with(imageView).load(url).transition(withCrossFade()).apply(options).into(imageView)
    }

    override fun displayPhotoViewImage(url: String, imageView: PhotoView, options: RequestOptions?) {
        if (options == null)
            Glide.with(imageView).load(url).into(imageView)
        else
            Glide.with(imageView).load(url).apply(options).into(imageView)
    }

}