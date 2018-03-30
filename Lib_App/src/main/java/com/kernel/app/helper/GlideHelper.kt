package com.kernel.app.helper

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.kernel.lib.base.config.GlideApp

/**
 * Created by hp on 2017/10/12.
 */

object GlideHelper {

    private var PLACE_CIRCLE = 0
    private var ERROR_CIRCLE = 0
    private var LONG_PLACE = 0
    private var LONG_ERROR = 0
    private var SQUARE_PLACE = 0
    private var SQUARE_ERROR = 0
    private var PLACE = 0
    private var ERROR = 0

    fun showCircle(context: Context, url: String, imageView: ImageView) {
        GlideApp.with(context)
                .load(url)
                .centerCrop()
                .apply(RequestOptions.bitmapTransform(CircleCrop()))
                .placeholder(PLACE_CIRCLE)
                .error(ERROR_CIRCLE)
                .into(imageView)
    }

    fun showPic(context: Context, url: String, imageView: ImageView) {
        GlideApp.with(context)
                .load(url)
                .centerCrop()
                .placeholder(PLACE)
                .error(ERROR)
                .into(imageView)
    }

    fun showNoCenterLongPic(context: Context, url: String, imageView: ImageView) {
        GlideApp.with(context)
                .load(url)
                .placeholder(LONG_PLACE)
                .error(LONG_ERROR)
                .into(imageView)
    }

    fun showLongPic(context: Context, url: String, imageView: ImageView) {
        GlideApp.with(context)
                .load(url)
                .centerCrop()
                .placeholder(LONG_PLACE)
                .error(LONG_ERROR)
                .into(imageView)
    }

    fun showSquarePic(context: Context, url: String, imageView: ImageView) {
        GlideApp.with(context)
                .load(url)
                .centerCrop()
                .placeholder(SQUARE_PLACE)
                .error(SQUARE_ERROR)
                .into(imageView)
    }

    fun showNoPlacePic(context: Context, drawable: Int, imageView: ImageView) {
        GlideApp.with(context)
                .load(drawable)
                .centerCrop()
                .into(imageView)
    }

    fun showNoCenterPlacePic(context: Context, url: String, imageView: ImageView) {
        GlideApp.with(context)
                .load(url)
                .into(imageView)
    }

    fun showNoPlacePic(context: Context, url: String, imageView: ImageView) {
        GlideApp.with(context)
                .load(url)
                .centerCrop()
                .into(imageView)
    }

    fun showNoPlaceNoCenterPic(context: Context, url: String, imageView: ImageView) {
        GlideApp.with(context)
                .load(url)
                .into(imageView)
    }

    fun showPreloadPic(context: Context, url: String, width: Int, height: Int) {
        GlideApp.with(context)
                .load(url)
                .preload(width, height)
    }

    fun setPlace(place: Int, error: Int, longPlace: Int, longError: Int, placeRound: Int, errorRound: Int, squarePlace: Int, squareError: Int) {
        PLACE = place
        ERROR = error
        LONG_PLACE = longPlace
        LONG_ERROR = longError
        PLACE_CIRCLE = placeRound
        ERROR_CIRCLE = errorRound
        SQUARE_PLACE = squarePlace
        SQUARE_ERROR = squareError
    }

}
