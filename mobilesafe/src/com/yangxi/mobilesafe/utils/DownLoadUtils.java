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
		// ����httputils����
		HttpUtils http = new HttpUtils();
		http.download(url, target, new RequestCallBack<File>() {
			@Override
			public void onSuccess(ResponseInfo<File> responseinfo) {
				// ���سɹ�ʱִ�еķ�����
				mProgressDialog.dismiss();
				File file = responseinfo.result;
				Log.i("DownLoadUtils", "�������");
			}

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// ����ʧ��ʱִ�еķ���
				Log.i("DownLoadUtils", "����ʧ��");
				mProgressDialog.setMessage("����ʧ��");
				mProgressDialog.dismiss();
			}

			@Override
			public void onStart() {
				// ��ʼ����
				Log.i("DownLoadUtils", "��ʼ����");
				super.onStart();
			}

			@Override
			public void onLoading(long total, long current, boolean isUploading) {
				// ��������
				Log.i("DownLoadUtils", "������...");
				mProgressDialog.setMessage("��������...");
				mProgressDialog.setMax((int) total);
				mProgressDialog.setProgress((int) current);
				super.onLoading(total, current, isUploading);
			}

		});

	}
	
	/**
	 * ��ʼ��һ��ProgressDialog
	 */
	public  void initProgressDialog() {
		mProgressDialog = new ProgressDialog(context);
		mProgressDialog.setMessage("׼������...");
		mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		mProgressDialog.show();
	}

	 
}
