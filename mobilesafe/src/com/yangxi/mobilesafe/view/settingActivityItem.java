package com.yangxi.mobilesafe.view;

import com.yangxi.mobilesafe.R;
import com.yangxi.mobilesafe.utils.sharePraferenceUtil;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;

public class settingActivityItem extends RelativeLayout {
	private View view;
	private CheckBox cb_chose;
	sharePraferenceUtil sp;
	public settingActivityItem(Context context) {
		// 调用有两个参数的构造函数
		this(context, null);
	}

	public settingActivityItem(Context context, AttributeSet attrs) {
		// 调用有三个参数的构造函数
		this(context, null, 0);
	}

	public settingActivityItem(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		view = View.inflate(context, R.layout.setting_activity_item, this);
		cb_chose = (CheckBox) view.findViewById(R.id.cb_chose);
		// 设置item的点击事件，当item被点击的时候，改变checkbox的选中状态
		view.setOnClickListener(new OnClickListener() {
			private boolean checkState;
			@Override
			public void onClick(View v) {
			//cb_chose.setChecked(true);
			// 判断checkbox的状态是否是选中的
			sp =new sharePraferenceUtil();
			if(cb_chose.isChecked())
			{
				  cb_chose.setChecked(false);
				  //sp.setCheckState(true);//保存选中状态
				  
			} else{ 
					cb_chose.setChecked(true);
				    sp.setCheckState(true);
				}
				// 将checkbox的状态保存到sp中，以便于记住checkbox的状态

			}
		});
	}
}
