package com.kernel.lib.helper

import com.kernel.lib.base.BaseApplication
import java.io.IOException
import java.util.zip.ZipEntry
import java.util.zip.ZipFile

/**
 * Created by hp on 2017/10/30.
 */

object AutomatizationHelper {

    fun getChannel(): String {
        val appinfo = BaseApplication.instance.getApplicationInfo()
        val sourceDir = appinfo.sourceDir
        var ret = ""
        var zipfile: ZipFile? = null
        try {
            zipfile = ZipFile(sourceDir)
            val entries = zipfile.entries()
            while (entries.hasMoreElements()) {
                val entry = entries.nextElement() as ZipEntry
                val entryName = entry.name
                if (entryName.startsWith("META-INF/channel")) {
                    ret = entryName.replace("/", "")
                    break
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            zipfile!!.close()
        }

        val split = ret.split("_".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        return if (split != null && split.size >= 2) {
            ret.substring(split[0].length + 1)
        } else {
            ""
        }
    }

}
