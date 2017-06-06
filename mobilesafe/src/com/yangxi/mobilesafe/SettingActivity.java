package com.yangxi.mobilesafe;

import com.yangxi.mobilesafe.utils.sharePraferenceUtil;
import com.yangxi.mobilesafe.view.settingActivityItem;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class SettingActivity extends Activity {
	sharePraferenceUtil sp;
	private settingActivityItem siv_item;

	// private boolean ischeck;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		initUpdata();

	}
	

	private void initUpdata() {
		int b = com.yangxi.mobilesafe.R.id.siv_item;
		//Toast.makeText(getApplicationContext(), "id:" + b, 0).show();

		 
			siv_item = (settingActivityItem) findViewById(R.id.siv_item);
		  
			siv_item.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					boolean	is = siv_item.isCheck();
					siv_item.setCheck(!is);
					
					//当原来是选中时，点击选不中
					//当原来是未选中时，点击选中
				}
			});
		 
			Toast.makeText(getApplicationContext(), "该控件id:" + b, 0).show();
			 
		

	}

}
