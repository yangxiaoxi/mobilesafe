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
	private sharePraferenceUtil sp;
	private boolean isCheck;
	private boolean sischeck;
	private TextView tv_title;
	public settingActivityItem(Context context) {
		// ���������������Ĺ��캯��
		this(context, null);
	}

	public settingActivityItem(Context context, AttributeSet attrs) {
		// ���������������Ĺ��캯��
		this(context, null, 0);
	}
	public settingActivityItem(final Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		 View view = View.inflate(context,R.layout.setting_activity_item,this);
		 //boolean spCheck = sp.getCheckState(context);
		 cb_chose = (CheckBox) view.findViewById(R.id.cb_chose);
		// cb_chose.setChecked(spCheck);
		 tv_des = (TextView) view.findViewById(R.id.tv_des);
		 tv_title = (TextView) findViewById(R.id.tv_title);
		 //ֱ����ʾ��ѡ���״̬
		// cb_chose.isChecked();
			// ����item�ĵ���¼�����item�������ʱ�򣬸ı�checkbox��ѡ��״̬
			view.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
				  isCheck = cb_chose.isChecked();
				  sischeck = setCheck(!isCheck);
				  sp = new sharePraferenceUtil();
	 			  sp.setCheckState(context,sischeck);//����ѡ��״
				}
			});
//			int count = attrs.getAttributeCount();
//			System.out.println(count);
//			for(int i=0;i<attrs.getAttributeCount();i++)
//			{
//			  String  name = attrs.getAttributeName(i);
//			  String value = attrs.getAttributeValue(i);
//			  System.out.println(name+value);
//			}
//				if(cb_chose.isChecked())
//			{
//					  cb_chose.setChecked(false);
//					  tv_des.setText("�Զ������ѹر�");
//				 sp = new sharePraferenceUtil();
//				sp.setCheckState(context,isCheck());//����ѡ��״̬
//					  
			//}
          // else{ 
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
     public boolean  setCheck(boolean isCheck){
    	 cb_chose.setChecked(isCheck);
    	 if(isCheck){
    		 tv_des.setText("�Զ������ѿ���");
    		 return isCheck;
    	 }
    	 else{
    		 tv_des.setText("�Զ������ѹر�");
    		 return !isCheck;
    	 }
     }
		 
			
	}




