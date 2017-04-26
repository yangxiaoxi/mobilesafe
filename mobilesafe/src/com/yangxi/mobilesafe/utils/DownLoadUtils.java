package com.yangxi.mobilesafe.utils;

import java.io.File;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
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
	private ProgressDialog mProgressDialog;
	private Activity context;
	public DownLoadUtils(Activity activity){
		 context = activity;
	}

	public void downapk(String url, String target) {
		//SplashActivity	 splash = new SplashActivity ();
		//final ProgressDialog mProgressdialog = splash.initProgressDialog();
		// 创建httputils对象
		HttpUtils http = new HttpUtils();
		http.download(url, target, new RequestCallBack<File>() {
			@Override
			public void onSuccess(ResponseInfo<File> responseinfo) {
				// 下载成功时执行的方法法
				mProgressDialog.dismiss();
				File file = responseinfo.result;
				Log.i("DownLoadUtils", "下载完成");
			}

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// 下载失败时执行的方法
				Log.i("DownLoadUtils", "下载失败");
				mProgressDialog.setMessage("下载失败");
				mProgressDialog.dismiss();
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
				mProgressDialog.setMessage("正在下载...");
				mProgressDialog.setMax((int) total);
				mProgressDialog.setProgress((int) current);
				super.onLoading(total, current, isUploading);
			}

		});

	}
	
	/**
	 * 初始化一个ProgressDialog
	 */
	public  void initProgressDialog() {
		mProgressDialog = new ProgressDialog(context);
		mProgressDialog.setMessage("准备下载...");
		mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		mProgressDialog.show();
	}

	 
}
