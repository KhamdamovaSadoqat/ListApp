package com.software.listapp.utils

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.software.listapp.R

object ImageDownloader {
    @SuppressLint("CheckResult")
    fun loadImage(
        context: Context,
        url: String,
        imageView: ImageView,
        @DrawableRes
        placeHolderRes: Int? = null,
        @DrawableRes
        errorRes: Int? = null,
    ) {
        val uri = Uri.parse(url)

        Glide.with(context)
            .load(uri)
            .apply {
                if (placeHolderRes != null) placeholder(placeHolderRes)
                else placeholder(R.drawable.bg_gradient_blue)
                if (errorRes != null) error(errorRes)
                else error(R.drawable.bg_gradient_blue)
                    .into(imageView)
            }
    }
}
