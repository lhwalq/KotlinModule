package com.kernel.lib.helper

import android.graphics.Bitmap

/**
 * RamHelper 内存释放
 *
 * @author linhuan 2015年7月9日 下午2:52:45
 */
object RamHelper {

    /**
     * freedBitmap 释放图片内存
     *
     * @param bitmapList
     *
     * @author linhuan 2015年7月9日 下午2:55:00
     */
    fun freedBitmap(bitmapList: List<Bitmap>) {
        for (bitmap in bitmapList) {
            freedBitmap(bitmap)
        }
    }

    /**
     * freedBitmap 释放图片内存
     *
     * @param bitmap
     *
     * @author linhuan 2015年7月9日 下午2:54:32
     */
    fun freedBitmap(bitmap: Bitmap) {
        if (Helper.isNotNull(bitmap)) {
            if (bitmap.isRecycled) {
                // 回收并且置为null
                bitmap.recycle()
                bitmap = null!!
            }
        }
    }

}
