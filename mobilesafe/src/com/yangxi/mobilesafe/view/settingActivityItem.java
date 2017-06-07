package com.yangxi.mobilesafe.view;

import java.util.zip.Inflater;

import com.yangxi.mobilesafe.R;
import com.yangxi.mobilesafe.utils.sharePraferenceUtil;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * @author 杨茜
 * 
 */
public class settingActivityItem extends RelativeLayout {

	private CheckBox cb_chose;
	private TextView tv_des;
	private sharePraferenceUtil sp;
	private boolean isCheck;
	private boolean sischeck;
	private TextView tv_title;
	String NAMESPACE = "http://schemas.android.com/apk/res/com.yangxi.mobilesafe";
	private String deson;
	private String desoff;
	private String title;

	public settingActivityItem(Context context) {

		super(context);
		inflate(context, null);
		// this(context, null);
	}

	public settingActivityItem(Context context, AttributeSet attrs) {
		// 调用有三个参数的构造函数
		super(context, attrs);
		// this(context, null, 0);
		inflate(context, attrs);

	}

	public settingActivityItem(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		inflate(context, attrs);
	}

	/**
	 * @return 根据checkbox判断item是否开启
	 */
	public boolean isCheck() {
		return cb_chose.isChecked();
	}

	public boolean setCheck(boolean isCheck) {
		cb_chose.setChecked(isCheck);
		if (isCheck) {
			tv_des.setText(deson);
			return isCheck;
		} else {
			tv_des.setText(desoff);
			return !isCheck;
		}

	}

	private void inflate(Context context, AttributeSet attrs) {
		// 将布局文件转化为一个view
		View view = View.inflate(context, R.layout.setting_activity_item, this);
		cb_chose = (CheckBox) view.findViewById(R.id.cb_chose);
		tv_des = (TextView) view.findViewById(R.id.tv_des);
		tv_title = (TextView) findViewById(R.id.tv_title);
		//获取自定义属性的值
		title = attrs.getAttributeValue(NAMESPACE, "Title");
		desoff = attrs.getAttributeValue(NAMESPACE, "desOff");
		deson = attrs.getAttributeValue(NAMESPACE, "desOn");
		//将获取的标题显示在控件上
		tv_title.setText(title);
		 
		Log.i("settingActivityItem", "attrs的数量" + attrs.getAttributeCount());
		Log.i("settingActivityItem", title);
		Log.i("settingActivityItem", desoff);
		Log.i("settingActivityItem", deson);
		
		 
		

	}

}
