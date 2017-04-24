package com.yangxi.mobilesafe.utils;

import java.io.File;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.view.View.OnLongClickListener;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

public class DownLoadUtils {
	public void downapk(Context context,String url ,String target){
		 //����httputils����
		 HttpUtils http = new HttpUtils();
		 http.download(url,target, new RequestCallBack<File>() {
			@Override
			public void onSuccess(ResponseInfo<File>  responseinfo) {
				 //���سɹ�ʱִ�еķ�����
				File file = responseinfo.result;
				Log.i("DownLoadUtils", "�������");
			}
			@Override
			public void onFailure(HttpException arg0, String arg1) {
				 //����ʧ��ʱִ�еķ���
				Log.i("DownLoadUtils", "����ʧ��");
			}
			@Override
			public void onStart() {
				 //��ʼ����
				Log.i("DownLoadUtils", "��ʼ����");
				super.onStart();
			} 
			@Override
			public void onLoading(long total, long current, boolean isUploading) {
				 //��������
				Log.i("DownLoadUtils", "������...");
				super.onLoading(total, current, isUploading);
			}
			
		});
			 
		 
     }
	 }

