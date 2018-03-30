package com.kernel.lib.base.config

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.AppGlideModule
import com.kernel.lib.base.BaseApplication
import java.io.InputStream

/**
 * Created by hp on 2017/10/9.
 */

@GlideModule
class DollGlideModule : AppGlideModule() {

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        //        builder.setDecodeFormat(DecodeFormat.PREFER_RGB_565);
        //设置磁盘缓存目录（和创建的缓存目录相同）
        val downloadDirectoryPath = SystemDirectory.IMAGE_DIR
        if (PackageManager.PERMISSION_GRANTED == BaseApplication.instance.checkCallingOrSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            //设置缓存的大小为100M
            val cacheSize = 100 * 1024 * 1024
            builder.setDiskCache(DiskLruCacheFactory(downloadDirectoryPath, cacheSize.toLong()))
        }
    }

    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        registry.replace(GlideUrl::class.java, InputStream::class.java, OkHttpUrlLoader.Factory())
    }

    override fun isManifestParsingEnabled(): Boolean {
        return false
    }
}
