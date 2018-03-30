package com.kernel.lib.base

import android.app.Activity
import android.app.AlertDialog.Builder
import android.webkit.JsResult
import android.webkit.WebChromeClient
import android.webkit.WebView
import com.kernel.lib.R


/**
 * BaseWebChromeClient 基类
 *
 * @author linhuan 2015年7月11日 上午10:45:14
 */
class BaseWebChromeClient(internal var act: Activity) : WebChromeClient() {

    // 对话框
    override fun onJsAlert(view: WebView, url: String, message: String, result: JsResult): Boolean {
        // 构建一个Builder来显示网页中的alert对话框
        val builder = Builder(act)
        builder.setTitle(R.string.base_builder_prompt)
        builder.setMessage(message)
//        builder.setPositiveButton(R.string.builder_determine, object : AlertDialog.OnClickListener {
//
//            override fun onClick(dialog: DialogInterface, which: Int) {
//                result.confirm()
//            }
//        })
        builder.setCancelable(false)
        builder.create()
        builder.show()
        return true
    }

    //	// 带按钮的对话框
    //	@Override
    //	public boolean onJsConfirm(WebView view, String url, String message, final JsResult result) {
    //		Builder builder = new Builder(act);
    //		builder.setTitle(R.string.builder_prompt);
    //		builder.setMessage(message);
    //		builder.setPositiveButton(R.string.builder_determine,
    //				new AlertDialog.OnClickListener() {
    //
    //					@Override
    //					public void onClick(DialogInterface dialog, int which) {
    //						result.confirm();
    //					}
    //
    //				});
    //		builder.setNeutralButton(android.R.string.cancel,
    //				new AlertDialog.OnClickListener() {
    //
    //					@Override
    //					public void onClick(DialogInterface dialog, int which) {
    //						result.cancel();
    //					}
    //
    //				});
    //		builder.setCancelable(false);
    //		builder.create();
    //		builder.show();
    //		return true;
    //	}
    //
    //	// 带输入框的对话框
    //	@Override
    //	public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, final JsPromptResult result) {
    //		LayoutInflater inflater = LayoutInflater.from(act);
    //		final View v = inflater.inflate(R.layout.prom_dialog, null);
    //		// 设置 TextView对应网页中的提示信息
    //		((TextView) v.findViewById(R.id.TextView_PROM)).setText(message);
    //		// 设置EditText对应网页中的输入框
    //		((EditText) v.findViewById(R.id.EditText_PROM)).setText(defaultValue);
    //		Builder builder = new Builder(act);
    //		builder.setTitle("带输入的对话框");
    //		builder.setView(v);
    //		builder.setPositiveButton(android.R.string.ok,
    //				new AlertDialog.OnClickListener() {
    //
    //					@Override
    //					public void onClick(DialogInterface dialog, int which) {
    //
    //						String value = ((EditText) v.findViewById(R.id.EditText_PROM)).getText().toString();
    //						result.confirm(value);
    //					}
    //				});
    //		builder.setNegativeButton(android.R.string.cancel,
    //				new AlertDialog.OnClickListener() {
    //
    //					@Override
    //					public void onClick(DialogInterface dialog, int which) {
    //						// TODO Auto-generated method stub
    //						result.cancel();
    //					}
    //				});
    //		builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
    //
    //					@Override
    //					public void onCancel(DialogInterface dialog) {
    //						// TODO Auto-generated method stub
    //						result.cancel();
    //					}
    //
    //				});
    //		builder.create();
    //		builder.show();
    //		return true;
    //	}
    //
    //	// 设置网页加载的进度条
    //	public void onProgressChanged(WebView view, int newProgress) {
    //		act.getWindow().setFeatureInt(Window.FEATURE_PROGRESS, newProgress * 100);
    //		super.onProgressChanged(view, newProgress);
    //	}
    //
    //	// 设置应用程序的标题
    //	public void onReceivedTitle(WebView view, String title) {
    //		act.setTitle(title);
    //		super.onReceivedTitle(view, title);
    //	}

}
