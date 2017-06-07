package com.yangxi.mobilesafe.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
public class sharePraferenceUtil {
   private SharedPreferences sp;
	public sharePraferenceUtil() {
	}

	/**
	 * @param context
	 * @param b 用来设置   是否开启更新的状态（设置界面）
	 */
	public void setCheckState(Context context,boolean b) {
		sp = context.getSharedPreferences("CheckState",Context.MODE_PRIVATE);
		Editor edit = sp.edit();
		//保存复选框的状态
		edit.putBoolean("isCheck", b);
		edit.commit();
	}
	
	public boolean getCheckState(Context context){
		sp = context.getSharedPreferences("CheckState",Context.MODE_PRIVATE );
	    Boolean isCheck = sp.getBoolean("isCheck",false);//获取checkbox的状态
	    return isCheck;
	}
	
	/**
	 * @param context
	 * @param b 要存储的字符串
	 */
	public   void putString(Context context,String b) {
		sp = context.getSharedPreferences("config",Context.MODE_PRIVATE);
		Editor edit = sp.edit();
		//保存复选框的状态
		edit.putString("password", b);
		edit.commit();
	}
	
	/**
	 * @param context
	 * @return  要取出的字符串
	 */
	public String getString(Context context){
		sp = context.getSharedPreferences("config",Context.MODE_PRIVATE );
		//默认保存为空
	    String password = sp.getString("password", "");//获取checkbox的状态
	    return password;
	}

}
