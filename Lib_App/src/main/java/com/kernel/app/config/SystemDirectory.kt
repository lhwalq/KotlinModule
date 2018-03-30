package com.kernel.lib.base.config

import android.content.Context
import android.os.Environment
import com.kernel.app.base.KernelApplication
import com.kernel.lib.R
import com.kernel.lib.helper.ResourceHelper
import java.io.File

object SystemDirectory {

    var BASE_DIR = KernelApplication.instance.getSdcard()
    var SAVE_VIDEO_DIR = BASE_DIR + "/save/video/"
    var SAVE_IMAGE_DIR = BASE_DIR + "/save/image/"
    var SAVE_DIR = BASE_DIR + "/save/"
    var CACHE_DIR = BASE_DIR + "/cache/"
    var IMAGE_DIR = CACHE_DIR + "/image/"

    fun hasFile(path: String): Boolean {
        return File(path).exists()
    }

    fun initDirectory(ctx: Context) {
        // 若SD卡不存在，则重置主要下载目录
        if (Environment.MEDIA_MOUNTED != Environment.getExternalStorageState()) {
            val private_file_dir = ctx.filesDir.absolutePath
            BASE_DIR = private_file_dir
            CACHE_DIR = BASE_DIR + "/cache/"
            IMAGE_DIR = CACHE_DIR + "/image/"
            SAVE_DIR = BASE_DIR + "/save/"
            SAVE_VIDEO_DIR = BASE_DIR + "/save/video/"
            SAVE_IMAGE_DIR = BASE_DIR + "/save/image/"
        }
        mkdirs(CACHE_DIR)
        mkdirs(IMAGE_DIR)
        mkdirs(SAVE_DIR)
        mkdirs(SAVE_VIDEO_DIR)
        mkdirs(SAVE_IMAGE_DIR)
    }

    private fun mkdirs(path: String) {
        try {
            val file = File(path)
            if (!file.exists()) {
                file.mkdirs()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

}
