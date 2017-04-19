package com.yangxi.mobilesafe.utils;


import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

public class getVersionInfoUtils {
	private static String mVerisionName;
	private static int mVersionCode;

	/**
	 * @param context
	 *            传过来一个上下文
	 * @return 获得本地的版本号
	 */
	// 获取本地版本号
	public static int getLocalVersionCode(Context context) {
		PackageManager packagemanager = context.getPackageManager();
		try {
			PackageInfo packageInfo = packagemanager.getPackageInfo(
					context.getPackageName(), 0);
			mVersionCode = packageInfo.versionCode;
			return mVersionCode;
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}

	}

	/**
	 * @param context
	 *            调用该方法时传过来一个上下文
	 * @return 本程序清单配置文件里面的版本名称
	 */
	// 获取本地版本名称
	public static String getLocalVersionName(Context context) {
		PackageManager packageManange = context.getPackageManager();
		try {
			PackageInfo packageInfo = packageManange.getPackageInfo(
					context.getPackageName(), 0);
			mVerisionName = packageInfo.versionName;
			return mVerisionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			return "";
		}

	}

}
