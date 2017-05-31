package com.yangxi.mobilesafe.view;

import com.yangxi.mobilesafe.R;
import com.yangxi.mobilesafe.utils.sharePraferenceUtil;

import android.content.Context;
import android.util.AttributeSet;
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
		 View view = View.inflate(context,R.layout.setting_activity_item,this);
		 cb_chose = (CheckBox) view.findViewById(R.id.cb_chose);
		 tv_des = (TextView) view.findViewById(R.id.tv_des);
		 
		 //ֱ����ʾ��ѡ���״̬
		// cb_chose.isChecked();
			// ����item�ĵ���¼�����item�������ʱ�򣬸ı�checkbox��ѡ��״̬
			view.setOnClickListener(new OnClickListener() {
				private boolean checkState;
				@Override
				public void onClick(View v) {
				//cb_chose.setChecked(true);
				// �ж�checkbox��״̬�Ƿ���ѡ�е�
				sharePraferenceUtil	sp =new sharePraferenceUtil();
				//�õ�֮ǰ��ѡ���ѡ��״̬
				boolean isCheck = cb_chose.isChecked();
				setCheck(!isCheck);
				}
			});
//				if(isCheck)
//				{
//					  cb_chose.setChecked(false);
//					  tv_des.setText("�Զ������ѹر�");
//					  //sp.setCheckState(true);//����ѡ��״̬
//					  
//				} else{ 
//						cb_chose.setChecked(true);
//						tv_des.setText("�Զ������ѿ���");
//						
//					    //sp.setCheckState(context,true);
//					}
//					// ��checkbox��״̬���浽sp�У��Ա��ڼ�סcheckbox��״̬
//	
//				}
			//});
		 
				
			}
		 
	
   /**
 * @return  ����checkbox�ж�item�Ƿ���
 */
     public boolean isCheck(){
     	 return cb_chose.isChecked();	
		}
     public void setCheck(boolean isCheck){
    	 cb_chose.setChecked(isCheck);
    	 if(isCheck){
    		 tv_des.setText("�Զ������ѿ���");
    	 }
    	 else{
    		 tv_des.setText("�Զ������ѹر�");
    	 }
    	 
     }
		 
			
	}




