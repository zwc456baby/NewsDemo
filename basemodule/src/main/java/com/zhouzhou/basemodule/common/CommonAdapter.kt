package com.zhouzhou.basemodule.common

import android.text.TextUtils
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade


class CommonAdapter {
    companion object {
        @BindingAdapter("android:src", "app:imageUrl")
        fun loadImage(view: ImageView, url: String) {
            if (!TextUtils.isEmpty(url)) {
                Glide.with(view.getContext())
                    .load(url)
                    .transition(withCrossFade())
                    .into(view)
            }
        }
    }

}