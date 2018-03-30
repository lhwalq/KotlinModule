package com.kernel.lib.helper

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.os.Environment
import android.widget.ImageView

import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

/**
 * Created by wangwn on 2016/5/30.
 */
object ImageHelper {


    /**
     * 保存图片到本地目录
     *
     * @param imageView 图片view
     */
    fun SaveImageToSysAlbum(context: Context, imageView: ImageView) {
        val bmpDrawable = imageView.drawable as BitmapDrawable
        val bmp = bmpDrawable.bitmap
        var saved: File? = null
        if (bmp != null) {
            try {
                val fileName = System.currentTimeMillis().toString() + ".png"
                val path = "/Download/"
                saved = saveFile(bmp, fileName, path)
                ToastHelper.showToast("图片已保存到$path$fileName")
            } catch (e: Exception) {
                saved = null
                e.printStackTrace()
            }

        } else {
            ToastHelper.showToast("找不到图片信息")
        }
        //通知更新图库
        if (saved != null) {
            val intent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
            val uri = Uri.fromFile(saved)
            intent.data = uri
            context.sendBroadcast(intent)
            context.applicationContext.sendBroadcast(Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + Environment.getExternalStorageDirectory())))
        }
    }

    /**
     * 保存图片
     *
     * @param bm       图片bmp
     * @param fileName 保存的文件名
     * @param path     保存的路径
     * @return 保存的File对象
     * @throws IOException
     */
    @Throws(IOException::class)
    fun saveFile(bm: Bitmap, fileName: String, path: String): File {
        val SAVE_PIC_PATH = if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED, ignoreCase = true)) Environment.getExternalStorageDirectory().absolutePath else "/mnt/sdcard"//保存到SD卡
        val subForder = SAVE_PIC_PATH + path
        val foder = File(subForder)
        if (!foder.exists()) {
            foder.mkdirs()
        }
        val myCaptureFile = File(subForder, fileName)
        if (!myCaptureFile.exists()) {
            myCaptureFile.createNewFile()
        }
        val bos = BufferedOutputStream(FileOutputStream(myCaptureFile))
        bm.compress(Bitmap.CompressFormat.PNG, 100, bos)
        bos.flush()
        bos.close()
        return myCaptureFile
    }

    /**
     * 保存视频缩略图
     *
     * @param videoPath
     * @param imgPath
     * @throws IOException
     */
    @Throws(IOException::class)
    fun saveFile(videoPath: String, imgPath: String): String {
        val bm = getVideoThumbnail(videoPath)
        val myCaptureFile = File(imgPath)
        if (!myCaptureFile.exists()) {
            myCaptureFile.createNewFile()
        }
        val bos = BufferedOutputStream(FileOutputStream(myCaptureFile))
        bm.compress(Bitmap.CompressFormat.JPEG, 100, bos)
        bos.flush()
        bos.close()
        return imgPath
    }

    fun getVideoThumbnail(videoPath: String): Bitmap {
        val media = MediaMetadataRetriever()
        media.setDataSource(videoPath)
        return media.frameAtTime
    }
}
