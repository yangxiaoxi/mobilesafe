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
 * @author ����
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
		// ���������������Ĺ��캯��
		super(context, attrs);
		// this(context, null, 0);
		inflate(context, attrs);

	}

	public settingActivityItem(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		inflate(context, attrs);
	}

	/**
	 * @return ����checkbox�ж�item�Ƿ���
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
		// �������ļ�ת��Ϊһ��view
		View view = View.inflate(context, R.layout.setting_activity_item, this);
		cb_chose = (CheckBox) view.findViewById(R.id.cb_chose);
		tv_des = (TextView) view.findViewById(R.id.tv_des);
		tv_title = (TextView) findViewById(R.id.tv_title);
		//��ȡ�Զ������Ե�ֵ
		title = attrs.getAttributeValue(NAMESPACE, "Title");
		desoff = attrs.getAttributeValue(NAMESPACE, "desOff");
		deson = attrs.getAttributeValue(NAMESPACE, "desOn");
		//����ȡ�ı�����ʾ�ڿؼ���
		tv_title.setText(title);
		 
		Log.i("settingActivityItem", "attrs������" + attrs.getAttributeCount());
		Log.i("settingActivityItem", title);
		Log.i("settingActivityItem", desoff);
		Log.i("settingActivityItem", deson);
		
		 
		

	}

}
