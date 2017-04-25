package com.yangxi.mobilesafe.utils;

import java.io.File;

import org.xml.sax.Parser;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.View.OnLongClickListener;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.yangxi.mobilesafe.SplashActivity;

public class DownLoadUtils {
	public void downapk(final ProgressDialog mProgressdialog,String url, String target) {
		// 创建httputils对象
		HttpUtils http = new HttpUtils();
		http.download(url, target, new RequestCallBack<File>() {
			@Override
			public void onSuccess(ResponseInfo<File> responseinfo) {
				// 下载成功时执行的方法法
				mProgressdialog.dismiss();
				File file = responseinfo.result;
				Log.i("DownLoadUtils", "下载完成");
			}

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// 下载失败时执行的方法
				Log.i("DownLoadUtils", "下载失败");
				mProgressdialog.setMessage("下载失败");
				mProgressdialog.dismiss();
			}

			@Override
			public void onStart() {
				// 开始下载
				Log.i("DownLoadUtils", "开始下载");
				super.onStart();
			}

			@Override
			public void onLoading(long total, long current, boolean isUploading) {
				// 正在下载
				Log.i("DownLoadUtils", "下载中...");
				mProgressdialog.setMessage("正在下载...");
				mProgressdialog.setMax((int) total);
				mProgressdialog.setProgress((int) current);
				super.onLoading(total, current, isUploading);
			}

		});

	}

	 
}
