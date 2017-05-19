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
		// ���������������Ĺ��캯��
		this(context, null);
	}

	public settingActivityItem(Context context, AttributeSet attrs) {
		// ���������������Ĺ��캯��
		this(context, null, 0);
	}

	public settingActivityItem(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		view = View.inflate(context, R.layout.setting_activity_item, this);
		cb_chose = (CheckBox) view.findViewById(R.id.cb_chose);
		// ����item�ĵ���¼�����item�������ʱ�򣬸ı�checkbox��ѡ��״̬
		view.setOnClickListener(new OnClickListener() {
			private boolean checkState;
			@Override
			public void onClick(View v) {
			//cb_chose.setChecked(true);
			// �ж�checkbox��״̬�Ƿ���ѡ�е�
			sp =new sharePraferenceUtil();
			if(cb_chose.isChecked())
			{
				  cb_chose.setChecked(false);
				  //sp.setCheckState(true);//����ѡ��״̬
				  
			} else{ 
					cb_chose.setChecked(true);
				    sp.setCheckState(true);
				}
				// ��checkbox��״̬���浽sp�У��Ա��ڼ�סcheckbox��״̬

			}
		});
	}
}
