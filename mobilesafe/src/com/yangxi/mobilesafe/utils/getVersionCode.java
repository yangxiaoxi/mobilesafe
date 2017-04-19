package com.yangxi.mobilesafe.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

public class getVersionCode {
public static int  getLocalVersionCode(Context context) {
	PackageManager packagemanager = context.getPackageManager();
	try {
		PackageInfo packageInfo = packagemanager.getPackageInfo(context.getPackageName(), 0);
		int versionCode = packageInfo.versionCode;
		return versionCode;
	} catch (NameNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return 0;
	}
		
	}

}
