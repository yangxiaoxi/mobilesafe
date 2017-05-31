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
	//private boolean ischeck;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
	// View view = View.inflate(getApplicationContext(), R.layout.setting_activity_item,null);
	settingActivityItem itemView = (settingActivityItem)this.findViewById(R.id.siv_item);
	int b = com.yangxi.mobilesafe.R.id.siv_item ;
	Toast.makeText(getApplicationContext(), "id:"+b,0).show();
//	//System.out.println(b);
//	itemView.setOnClickListener(new OnClickListener() {
//		
//		@Override
//		public void onClick(View v) {
//			// TODO Auto-generated method stub
//			
//		}
//	});
}

	 
}
