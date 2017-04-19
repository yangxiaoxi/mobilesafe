package com.yangxi.mobilesafe.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;

public class getVersionName {
	static String Tag = "SplashActivity";
	 
	/**
	 * @param context
	 *     ���ø÷���ʱ������һ��������
	 * @return 
	 *     �������嵥�����ļ�����İ汾����
	 */  
	public static String getLocalVersionName(Context context) {
		PackageManager packageManange = context.getPackageManager();
		try {
			PackageInfo packageInfo = packageManange.getPackageInfo(context.getPackageName(),0);
			String verisionName = packageInfo.versionName;
			Log.i(Tag, verisionName);
			return verisionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			return "";
		}

	}


}
