package com.yangxi.mobilesafe.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
public class sharePraferenceUtil {
   private  Context context;
   private SharedPreferences sp;
	public sharePraferenceUtil() {
	}

	public void setCheckState(boolean b) {
		sp = context.getSharedPreferences("CheckState",Context.MODE_PRIVATE);
		Editor edit = sp.edit();
		//���渴ѡ���״̬
		edit.putBoolean("isCheck", b);
		edit.commit();
	}
	
	public boolean getCheckState(){
		sp = context.getSharedPreferences("CheckState",Context.MODE_PRIVATE );
	    Boolean isCheck = sp.getBoolean("isCheck",false);//��ȡcheckbox��״̬
	    return isCheck;
	}

}
