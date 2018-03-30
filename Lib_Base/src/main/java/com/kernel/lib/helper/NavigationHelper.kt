package com.kernel.lib.helper

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.provider.MediaStore.Audio.Media
import android.provider.Settings
import android.support.v4.content.FileProvider
import com.kernel.lib.R
import com.kernel.lib.base.BaseApplication
import com.kernel.lib.base.BaseCompatActivity
import com.kernel.lib.base.BaseConstants

import java.io.File


/**
 * function: 导航、跳转(startActivity、startActivityForResult、finish) 助手类
 *
 * slideActivity：默认进入动画从左到右，后退动画从右到左
 * slideActivityForResult
 * pushupActivity：默认进入动画从下推出，后退动画从上推入
 * pushupActivityForResult
 * showActivity: 显示类似窗口的页面
 *
 * startActivity：可自定义动画
 * startActivityForResult：可自定义动画
 *
 * finish: 代替finish
 *
 * @author:linhuan
 */
object NavigationHelper {

    private val ANDROID_NOT_HAS_CROP = 4                                            // 当Android系统不存在截图时


    /**
     * 欢迎页出现
     *
     * @param act
     * @param toActivity
     * @param bundle
     * @param finish 是否关闭当前界面
     */
    fun fadeInActivity(act: Activity, toActivity: Class<*>, bundle: Bundle, finish: Boolean) {
        var bundle = bundle
        if (Helper.isNull(bundle)) {
            bundle = Bundle()
        }
        bundle.putString(BaseConstants.ActivityInfo.BUNDLEKEY_ACTIVITYANIMTYPE, BaseConstants.ActivityInfo.ACTIVITYANIMTYPE_ALPHA)
        startActivity(act, toActivity, bundle, finish, android.R.anim.fade_in, android.R.anim.fade_out, 0, 0)
    }

    /**
     * 渐变出现
     *
     * @param act
     * @param toActivity
     * @param bundle
     * @param finish 是否关闭当前界面
     */
    fun alphaActivity(act: Activity, toActivity: Class<*>, bundle: Bundle, finish: Boolean) {
        var bundle = bundle
        if (Helper.isNull(bundle)) {
            bundle = Bundle()
        }
        bundle.putString(BaseConstants.ActivityInfo.BUNDLEKEY_ACTIVITYANIMTYPE, BaseConstants.ActivityInfo.ACTIVITYANIMTYPE_ALPHA)
        startActivity(act, toActivity, bundle, finish, R.anim.base_push_alpha_in, R.anim.base_push_alpha_out, 0, 0)
    }

    /**
     * 渐变出现
     *
     * @param act
     * @param toActivity
     * @param bundle
     * @param requestCode
     */
    fun alphaActivityForResult(act: Activity, toActivity: Class<*>, bundle: Bundle, requestCode: Int) {
        var bundle = bundle
        if (Helper.isNull(bundle)) {
            bundle = Bundle()
        }
        bundle.putString(BaseConstants.ActivityInfo.BUNDLEKEY_ACTIVITYANIMTYPE, BaseConstants.ActivityInfo.ACTIVITYANIMTYPE_ALPHA)
        startActivityForResult(act, toActivity, bundle, requestCode, R.anim.base_push_alpha_in, R.anim.base_push_alpha_out, 0, 0)
    }

    /**
     * 从左往右返回首页
     *
     * @param act
     * @param toActivity
     */
    fun slideHomeActivity(act: Activity, toActivity: Class<*>) {
        val intent = Intent(act, toActivity)
        act.startActivity(intent)
        act.finish()
        act.overridePendingTransition(R.anim.base_pull_right_in, R.anim.base_pull_right_out)
    }

    /**
     * 从右往左
     *
     * @param act
     * @param toActivity
     * @param bundle
     * @param finish 是否关闭当前界面
     */
    fun slideActivity(act: Activity, toActivity: Class<*>, bundle: Bundle, finish: Boolean) {
        var bundle = bundle
        if (Helper.isNull(bundle)) {
            bundle = Bundle()
        }
        bundle.putString(BaseConstants.ActivityInfo.BUNDLEKEY_ACTIVITYANIMTYPE, BaseConstants.ActivityInfo.ACTIVITYANIMTYPE_SLIDE)
        startActivity(act, toActivity, bundle, finish, R.anim.base_push_left_in, R.anim.base_push_left_out, 0, 0)
    }

    /**
     * 从右往左，接收返回数据
     *
     * @param act
     * @param toActivity
     * @param bundle
     * @param requestCode
     */
    fun slideActivityForResult(act: Activity, toActivity: Class<*>, bundle: Bundle, requestCode: Int) {
        var bundle = bundle
        if (Helper.isNull(bundle)) {
            bundle = Bundle()
        }
        bundle.putString(BaseConstants.ActivityInfo.BUNDLEKEY_ACTIVITYANIMTYPE, BaseConstants.ActivityInfo.ACTIVITYANIMTYPE_SLIDE)
        startActivityForResult(act, toActivity, bundle, requestCode, R.anim.base_push_left_in, R.anim.base_push_left_out, 0, 0)
    }

    /**
     * 从下往上
     *
     * @param act
     * @param toActivity
     * @param bundle
     * @param finish
     */
    fun pushupActivity(act: Activity, toActivity: Class<*>, bundle: Bundle, finish: Boolean) {
        var bundle = bundle
        if (Helper.isNull(bundle)) {
            bundle = Bundle()
        }
        bundle.putString(BaseConstants.ActivityInfo.BUNDLEKEY_ACTIVITYANIMTYPE, BaseConstants.ActivityInfo.ACTIVITYANIMTYPE_PUSHUP)
        startActivity(act, toActivity, bundle, finish, R.anim.base_push_up_in, R.anim.base_push_up_out, 0, 0)
    }

    /**
     * 从上往下
     *
     * @param act
     * @param toActivity
     * @param bundle
     * @param finish
     */
    fun pushHeadActivity(act: Activity, toActivity: Class<*>, bundle: Bundle, finish: Boolean) {
        var bundle = bundle
        if (Helper.isNull(bundle)) {
            bundle = Bundle()
        }
        bundle.putString(BaseConstants.ActivityInfo.BUNDLEKEY_ACTIVITYANIMTYPE, BaseConstants.ActivityInfo.ACTIVITYANIMTYPE_PUSHUP)
        startActivity(act, toActivity, bundle, finish, R.anim.base_pull_head_in, R.anim.base_pull_head_out, 0, 0)
    }


    /**
     * 从下往上，接收返回数据
     *
     * @param act
     * @param toActivity
     * @param bundle
     * @param requestCode
     */
    fun pushupActivityForResult(act: Activity, toActivity: Class<*>, bundle: Bundle, requestCode: Int) {
        var bundle = bundle
        if (Helper.isNull(bundle)) {
            bundle = Bundle()
        }
        bundle.putString(BaseConstants.ActivityInfo.BUNDLEKEY_ACTIVITYANIMTYPE, BaseConstants.ActivityInfo.ACTIVITYANIMTYPE_PUSHUP)
        startActivityForResult(act, toActivity, bundle, requestCode, R.anim.base_push_up_in, R.anim.base_push_up_out, 0, 0)
    }

    /**
     * 从下往上
     *
     * @param act
     * @param toActivity
     * @param bundle
     * @param finish
     */
    fun centerActivity(act: Activity, toActivity: Class<*>, bundle: Bundle, finish: Boolean) {
        var bundle = bundle
        if (Helper.isNull(bundle)) {
            bundle = Bundle()
        }
        bundle.putString(BaseConstants.ActivityInfo.BUNDLEKEY_ACTIVITYANIMTYPE, BaseConstants.ActivityInfo.ACTIVITYANIMTYPE_CENTER)
        startActivity(act, toActivity, bundle, finish, R.anim.base_push_center_in, R.anim.base_push_center_out, 0, 0)
    }

    /**
     * 从下往上，接收返回数据
     *
     * @param act
     * @param toActivity
     * @param bundle
     * @param requestCode
     */
    fun centerActivityForResult(act: Activity, toActivity: Class<*>, bundle: Bundle, requestCode: Int) {
        var bundle = bundle
        if (Helper.isNull(bundle)) {
            bundle = Bundle()
        }
        bundle.putString(BaseConstants.ActivityInfo.BUNDLEKEY_ACTIVITYANIMTYPE, BaseConstants.ActivityInfo.ACTIVITYANIMTYPE_CENTER)
        startActivityForResult(act, toActivity, bundle, requestCode, R.anim.base_push_center_in, R.anim.base_push_center_out, 0, 0)
    }


    fun startActivity(act: Activity, toActivity: Class<*>, bundle: Bundle, finish: Boolean, enterAnim: Int, exitAnim: Int, backEnterAnim: Int, backExitAnim: Int) {
        var bundle = bundle
        val intent = Intent(act, toActivity)
        if (Helper.isNull(bundle)) {
            bundle = Bundle()
        }
        bundle.putInt(BaseConstants.ActivityInfo.BUNDLEKEY_BACKENTERANIM, backEnterAnim)
        bundle.putInt(BaseConstants.ActivityInfo.BUNDLEKEY_BACKEXITANIM, backExitAnim)
        intent.putExtras(bundle)
        act.startActivity(intent)
        if (finish) {
            act.finish()
        }
        act.overridePendingTransition(enterAnim, exitAnim)
    }

    fun startActivityForResult(act: Activity, toActivity: Class<*>, bundle: Bundle, requestCode: Int, enterAnim: Int, exitAnim: Int, backEnterAnim: Int, backExitAnim: Int) {
        var bundle = bundle
        val intent = Intent(act, toActivity)
        if (Helper.isNull(bundle)) {
            bundle = Bundle()
        }
        bundle.putInt(BaseConstants.ActivityInfo.BUNDLEKEY_BACKENTERANIM, backEnterAnim)
        bundle.putInt(BaseConstants.ActivityInfo.BUNDLEKEY_BACKENTERANIM, backExitAnim)
        intent.putExtras(bundle)
        act.startActivityForResult(intent, requestCode)
        act.overridePendingTransition(enterAnim, exitAnim)
    }

    /**
     * 关闭界面
     *
     * @param act
     * @param resultCode
     * @param intent
     */
    fun finish(act: Activity, resultCode: Int, intent: Intent?) {
        if (Helper.isNull(intent)) {
            act.setResult(resultCode)
        } else {
            act.setResult(resultCode, intent)
        }
        act.finish()

        if (act is BaseCompatActivity<*, *>) {
            act.setBackAnim()
        }
    }

    /**
     * 设置返回数据
     *
     * @param act
     * @param resultCode
     * @param intent
     */
    fun setResult(act: Activity, resultCode: Int, intent: Intent) {
        if (Helper.isNull(intent)) {
            act.setResult(resultCode)
        } else {
            act.setResult(resultCode, intent)
        }
    }

    /**
     * 去设置界面
     */
    fun goSettingActivity(request: Int) {
        try {
            val intent = Intent()
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            intent.data = Uri.fromParts("package", BaseApplication.instance.getPackageName(), null)
            if (-1 != request) {
                AppManager.appManager.currentActivity()!!.startActivityForResult(intent, request)
            } else {
                BaseApplication.instance.startActivity(intent)
            }
        } catch (e: Exception) {

        }

    }

    fun installPackageByPackageName(apkPath: String, request: Int) {
        val file = File(apkPath)
        if (!file.exists()) {
            return
        }
        //		Intent intent = new Intent(Intent.ACTION_VIEW);
        //        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //		intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");

        val intent = Intent(Intent.ACTION_VIEW)
        // 判断是否是AndroidN以及更高的版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
            val contentUri = FileProvider.getUriForFile(BaseApplication.instance, BaseApplication.instance.getPackageName() + ".fileProvider", File(apkPath))
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive")
        } else {
            intent.setDataAndType(Uri.fromFile(File(apkPath)), "application/vnd.android.package-archive")
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }

        AppManager.appManager.currentActivity()!!.startActivityForResult(intent, request)
    }

    /**
     * function: 去拨号页面
     *
     * @param context
     * @param phoneNumber
     *
     * @author:linhuan 2011-11-17 下午03:27:57
     */
    fun goCallPhone(context: Context, phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }

    /**
     * function: 直接打电话
     *
     * @param context
     * @param phoneNumber
     *
     * @author:linhuan 2011-12-27 下午05:16:57
     */
    //	public static void callPhone(Context context, String phoneNumber) {
    //		Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
    //		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
    //		if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE)) {
    //			//    ActivityCompat#requestPermissions
    //			// here to request the missing permissions, and then overriding
    //			//   public void onRequestPermissionsResult(int requestCode, String[] permissions,
    //			//                                          int[] grantResults)
    //			// to handle the case where the user grants the permission. See the documentation
    //			// for ActivityCompat#requestPermissions for more details.
    //			return;
    //		}
    //		context.startActivity(intent);
    //	}

    /**
     * function: 打开图片
     *
     * @param act
     * @param filePath
     * @param requestCode
     *
     * @author:linhuan 2011-11-24 上午09:42:20
     */
    fun openImage(act: Activity, filePath: String, requestCode: Int) {
        try {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setDataAndType(Uri.parse("file://$filePath"), "image/*")
            act.startActivityForResult(intent, requestCode)
        } catch (e: ActivityNotFoundException) {
            ToastHelper.showToast(act.getString(R.string.base_errormsg_cantopenimage))
            return
        }

    }

    /**
     * function: 播放音频
     *
     * @param act
     * @param filePath
     * @param requestCode
     *
     * @author:linhuan 2011-11-24 上午09:43:00
     */
    fun openAudio(act: Activity, filePath: String, requestCode: Int) {
        try {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setDataAndType(Uri.parse("file://$filePath"), "audio/*")
            act.startActivityForResult(intent, requestCode)
        } catch (e: ActivityNotFoundException) {
            ToastHelper.showToast(act.getString(R.string.base_errormsg_cantplayaudio))
            return
        }

    }

    /**
     * function: 打开视频
     *
     * @param act
     * @param filePath
     * @param requestCode
     *
     * @author:linhuan 2011-11-24 上午09:43:10
     */
    fun openVideo(act: Activity, filePath: String, requestCode: Int) {
        try {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setDataAndType(Uri.parse("file://$filePath"), "video/*")
            act.startActivityForResult(intent, requestCode)
        } catch (e: ActivityNotFoundException) {
            ToastHelper.showToast(act.getString(R.string.base_errormsg_cantplayvedio))
            return
        }

    }

    /**
     * function: 选择图像
     *
     * @param act
     * @param requestCode
     *
     * @author:linhuan 2011-11-24 上午09:38:32
     */
    fun pickImage(act: Activity, requestCode: Int) {
        val i = Intent()
        i.type = "image/*"
        i.action = Intent.ACTION_GET_CONTENT
        act.startActivityForResult(Intent.createChooser(i, act.getString(R.string.base_fwtitle_selectimage)), requestCode)
    }

    /**
     * function: pickSystemImage 开启系统相册
     *
     * @param act
     * @param requestCode
     *
     * @author:linhuan 2014-8-7 下午4:26:12
     */
    fun pickSystemImage(act: Activity, requestCode: Int): Boolean {
        val intentCrop = Intent("com.android.camera.action.CROP")
        intentCrop.type = "image/*"
        val list = act.packageManager.queryIntentActivities(intentCrop, 0)
        if (0 == list.size) {
            return true
        } else {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*")
            act.startActivityForResult(Intent.createChooser(intent, act.getString(R.string.base_fwtitle_selectimage)), requestCode)
            return false
        }
    }

    /**
     * function: 裁剪图片
     *
     * @param uri
     * @param width
     * @param act
     * @param requestCode
     *
     * @author:linhuan 2012-7-11 下午5:27:33
     */
    fun cropImage(uri: Uri, width: Int, act: Activity, requestCode: Int, camera: String) {
        val intent = Intent("com.android.camera.action.CROP")
        if (Helper.isNotNull(uri)) {
            intent.setDataAndType(uri, "image/*")
        }

        val list = act.packageManager.queryIntentActivities(intent, 0)
        //        int size = list.size();
        //        if (size == 0) {
        //        	Intent intentCrop = new Intent(act, CropImage.class);
        //        	intentCrop.putExtra(CropImage.IMAGE_PATH, camera);
        //        	intentCrop.putExtra(CropImage.SCALE, true);
        //        	intentCrop.putExtra(CropImage.ASPECT_X, 1);
        //        	intentCrop.putExtra(CropImage.ASPECT_Y, 1);
        //        	intentCrop.putExtra(CropImage.OUTPUT_X, width);
        //        	intentCrop.putExtra(CropImage.OUTPUT_Y, width);
        //        	act.startActivityForResult(intentCrop, ANDROID_NOT_HAS_CROP);
        //        } else {
        intent.putExtra("crop", "true")
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1)
        intent.putExtra("aspectY", 1)

        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", width)
        intent.putExtra("outputY", width)

        intent.putExtra("noFaceDetection", true)
        intent.putExtra("return-data", true)

        val i = Intent(intent)
        val res = list[0]

        i.component = ComponentName(res.activityInfo.packageName, res.activityInfo.name)

        act.startActivityForResult(intent, requestCode)
        //		}
    }

    /**
     * function: 裁剪图片
     *
     * @param uri
     * @param width
     * @param act
     * @param requestCode
     *
     * @author:linhuan 2012-7-11 下午5:27:33
     */
    fun cropImage(uri: Uri, width: Int, act: Activity, requestCode: Int) {
        val intent = Intent("com.android.camera.action.CROP")
        intent.setDataAndType(uri, "image/*")
        intent.putExtra("crop", "true")

        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1)
        intent.putExtra("aspectY", 1)

        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", width)
        intent.putExtra("outputY", width)

        intent.putExtra("noFaceDetection", true)
        intent.putExtra("return-data", true)

        act.startActivityForResult(intent, requestCode)
    }

    /**
     * function: 裁剪图片
     *
     * @param uri
     * @param width
     * @param height
     * @param act
     * @param requestCode
     *
     * @author:linhuan 2014-8-7 下午3:04:59
     */
    fun cropImage(uri: Uri, width: Int, height: Int, act: Activity, requestCode: Int) {
        val intent = Intent("com.android.camera.action.CROP")
        intent.setDataAndType(uri, "image/*")
        intent.putExtra("crop", "true")

        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1)
        intent.putExtra("aspectY", 1)

        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", width)
        intent.putExtra("outputY", height)

        intent.putExtra("noFaceDetection", true)
        intent.putExtra("return-data", true)

        act.startActivityForResult(intent, requestCode)
    }


    /**
     * function: 选择音频
     *
     * @param act
     * @param requestCode
     *
     * @author:linhuan 2011-11-24 上午09:38:47
     */
    fun pickAudio(act: Activity, requestCode: Int) {
        val i = Intent()
        i.type = "audio/*"
        i.action = Intent.ACTION_GET_CONTENT
        act.startActivityForResult(Intent.createChooser(i, act.getString(R.string.base_fwtitle_selectaudio)), requestCode)
    }

    /**
     * function: 选择视频
     *
     * @param act
     * @param requestCode
     *
     * @author:linhuan 2011-11-24 上午09:38:55
     */
    fun pickVedio(act: Activity, requestCode: Int) {
        val i = Intent()
        i.type = "video/*"
        i.action = Intent.ACTION_GET_CONTENT
        act.startActivityForResult(Intent.createChooser(i, act.getString(R.string.base_fwtitle_selectvedio)), requestCode)
    }

    /**
     * function: openSystemImage 直接开启系统相机
     *
     * @param act
     * @param requestCode
     * @param outputPath
     *
     * @author:linhuan 2014-8-7 下午2:13:14
     */
    fun openSystemImage(act: Activity, requestCode: Int, outputPath: String) {
        try {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            val list = act.packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY)
            for (resolveInfo in list) {
                if (resolveInfo.activityInfo.applicationInfo.sourceDir.startsWith("/system/app")) {
                    intent.setClassName(resolveInfo.activityInfo.applicationInfo.packageName, resolveInfo.activityInfo.name)
                    break
                }
            }
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(File(outputPath)))
            act.startActivityForResult(intent, requestCode)
        } catch (e: ActivityNotFoundException) {
            ToastHelper.showToast(act.getString(R.string.base_errormsg_cantopencamera))
            return
        }

    }

    /**
     * function: 照相
     *
     * @param ctx
     * @param requestCode
     * @param outputPath
     *
     * @author:linhuan 2011-11-24 上午09:40:55
     */
    fun takeImage(ctx: Context, requestCode: Int, outputPath: String) {
        try {
            val intent = Intent()
            intent.action = MediaStore.ACTION_IMAGE_CAPTURE
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(File(outputPath)))
            (ctx as Activity).startActivityForResult(intent, requestCode)
        } catch (e: ActivityNotFoundException) {
            ToastHelper.showToast(ctx.getString(R.string.base_errormsg_cantopencamera))
            return
        }

    }

    /**
     * function: 录音
     *
     * @param ctx
     * @param requestCode
     *
     * @author:linhuan 2011-11-24 上午09:41:12
     */
    fun takeAudio(ctx: Context, requestCode: Int) {
        try {
            val intent = Intent()
            intent.action = Media.RECORD_SOUND_ACTION
            (ctx as Activity).startActivityForResult(intent, requestCode)
        } catch (e: ActivityNotFoundException) {
            ToastHelper.showToast(ctx.getString(R.string.base_errormsg_cantopentaperecord))
            return
        }

    }

    /**
     * function: 摄像
     *
     * @param ctx
     * @param requestCode
     *
     * @author:linhuan 2011-11-24 上午09:41:50
     */
    fun takeVideo(ctx: Context, requestCode: Int) {
        try {
            val intent = Intent()
            intent.action = MediaStore.ACTION_VIDEO_CAPTURE
            (ctx as Activity).startActivityForResult(intent, requestCode)
        } catch (e: ActivityNotFoundException) {
            ToastHelper.showToast(ctx.getString(R.string.base_errormsg_cantopenvideocamera))
            return
        }

    }

    /**
     * function: 到发送短信页面
     *
     * @param ctx
     * @param content
     *
     * @author:linhuan 2011-12-15 下午03:07:37
     */
    fun sendSms(ctx: Context, content: String) {
        val smsUri = Uri.parse("tel:")
        val intent = Intent(Intent.ACTION_VIEW, smsUri)
        intent.putExtra("sms_body", content)
        intent.type = "vnd.android-dir/mms-sms"
        ctx.startActivity(intent)
    }

    /**
     * function: 安装apk
     *
     * @param path
     * @param ctx
     *
     * @author:linhuan 2011-12-26 下午03:24:24
     */
    fun installApk(path: String, ctx: Context) {
        val intent = Intent()
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.action = Intent.ACTION_VIEW
        intent.setDataAndType(Uri.parse("file://$path"), "application/vnd.android.package-archive")
        ctx.startActivity(intent)
    }

    /**
     * function: 用浏览器下载apk, 或者打开html页面
     *
     * @param url
     * @param act
     *
     * @author:linhuan 2012-2-9 上午11:15:29
     */
    fun downApkWithBrowse(url: String, act: Activity) {
        try {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            act.startActivityForResult(intent, 0)
        } catch (e: ActivityNotFoundException) {
            ToastHelper.showToast(act.getString(R.string.base_errormsg_cantopenbrowser))
        }

    }

    /**
     * function: 跳转到手机上拥有的市场中对应该项目的下载页面.可以进行评价等等
     *
     * @param act
     * @param pacakgeName 包名称
     * @param noMarketMessage 当前手机没有 安装市场平台的提示信息,默认为:亲,您木有安装第三方下载平台!
     *
     * @author:linhuan 2014年7月16日 下午2:20:32
     */
    fun turnMarket(act: Activity, pacakgeName: String, noMarketMessage: String) {
        var noMarketMessage = noMarketMessage
        try {
            val localIntent = Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$pacakgeName"))
            act.startActivity(localIntent)
        } catch (e: ActivityNotFoundException) {
            if (Helper.isEmpty(noMarketMessage)) {
                noMarketMessage = act.getString(R.string.base_errormsg_cantopenandroidmarket_forinstall)
            }
            ToastHelper.showToast(noMarketMessage)
        }

    }

    /**
     * function: 通过选择浏览器打开连接
     *
     * @param url
     * @param act
     *
     * @author:linhuan 2014年7月16日 下午2:22:49
     */
    fun openBrowse(url: String, act: Activity) {
        var url = url
        //		try {
        if (!(url.startsWith("http://") || url.startsWith("https://"))) {
            url = "http://$url"
        }
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        //			intent.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
        //			act.startActivityForResult(intent, 0);
        act.startActivity(Intent.createChooser(intent, "请选择浏览器"))
        //			act.startActivity(intent);
        //		} catch (ActivityNotFoundException e) {
        //			ToastHelper.showToast(act.getString(R.string.errormsg_cantopenandroidmarket_forweb));
        //		}
    }

    /**
     * 拨打电话
     *
     * @param num
     * @param act
     */
    fun openCall(num: String, act: Activity) {
        try {
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$num"))
            act.startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            ToastHelper.showToast(act.getString(R.string.base_errormsg_cantopenandroidmarket_call))
        }

    }

}
