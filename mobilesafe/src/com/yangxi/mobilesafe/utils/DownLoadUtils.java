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
		// ����httputils����
		HttpUtils http = new HttpUtils();
		http.download(url, target, new RequestCallBack<File>() {
			@Override
			public void onSuccess(ResponseInfo<File> responseinfo) {
				// ���سɹ�ʱִ�еķ�����
				mProgressdialog.dismiss();
				File file = responseinfo.result;
				Log.i("DownLoadUtils", "�������");
			}

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// ����ʧ��ʱִ�еķ���
				Log.i("DownLoadUtils", "����ʧ��");
				mProgressdialog.setMessage("����ʧ��");
				mProgressdialog.dismiss();
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
				mProgressdialog.setMessage("��������...");
				mProgressdialog.setMax((int) total);
				mProgressdialog.setProgress((int) current);
				super.onLoading(total, current, isUploading);
			}

		});

	}

	 
}
