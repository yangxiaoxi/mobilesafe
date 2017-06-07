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
	 * @param b ��������   �Ƿ������µ�״̬�����ý��棩
	 */
	public void setCheckState(Context context,boolean b) {
		sp = context.getSharedPreferences("CheckState",Context.MODE_PRIVATE);
		Editor edit = sp.edit();
		//���渴ѡ���״̬
		edit.putBoolean("isCheck", b);
		edit.commit();
	}
	
	public boolean getCheckState(Context context){
		sp = context.getSharedPreferences("CheckState",Context.MODE_PRIVATE );
	    Boolean isCheck = sp.getBoolean("isCheck",false);//��ȡcheckbox��״̬
	    return isCheck;
	}
	
	/**
	 * @param context
	 * @param b Ҫ�洢���ַ���
	 */
	public   void putString(Context context,String b) {
		sp = context.getSharedPreferences("config",Context.MODE_PRIVATE);
		Editor edit = sp.edit();
		//���渴ѡ���״̬
		edit.putString("password", b);
		edit.commit();
	}
	
	/**
	 * @param context
	 * @return  Ҫȡ�����ַ���
	 */
	public String getString(Context context){
		sp = context.getSharedPreferences("config",Context.MODE_PRIVATE );
		//Ĭ�ϱ���Ϊ��
	    String password = sp.getString("password", "");//��ȡcheckbox��״̬
	    return password;
	}

}
