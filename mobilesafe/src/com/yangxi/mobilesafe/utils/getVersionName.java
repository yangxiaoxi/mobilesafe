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
	 *     调用该方法时传过来一个上下文
	 * @return 
	 *     本程序清单配置文件里面的版本名称
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
