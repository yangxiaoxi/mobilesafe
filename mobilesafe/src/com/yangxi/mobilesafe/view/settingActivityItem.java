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
 * @author 杨茜
 *
 */
public class settingActivityItem extends RelativeLayout {
	
	private CheckBox cb_chose;
	private TextView tv_des;

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
		 View view = View.inflate(context,R.layout.setting_activity_item,this);
		 cb_chose = (CheckBox) view.findViewById(R.id.cb_chose);
		 tv_des = (TextView) view.findViewById(R.id.tv_des);
		 
		 //直接显示复选框的状态
		// cb_chose.isChecked();
			// 设置item的点击事件，当item被点击的时候，改变checkbox的选中状态
			view.setOnClickListener(new OnClickListener() {
				private boolean checkState;
				@Override
				public void onClick(View v) {
				//cb_chose.setChecked(true);
				// 判断checkbox的状态是否是选中的
				sharePraferenceUtil	sp =new sharePraferenceUtil();
				//拿到之前复选框的选中状态
				boolean isCheck = cb_chose.isChecked();
				setCheck(!isCheck);
				}
			});
//				if(isCheck)
//				{
//					  cb_chose.setChecked(false);
//					  tv_des.setText("自动更新已关闭");
//					  //sp.setCheckState(true);//保存选中状态
//					  
//				} else{ 
//						cb_chose.setChecked(true);
//						tv_des.setText("自动更新已开启");
//						
//					    //sp.setCheckState(context,true);
//					}
//					// 将checkbox的状态保存到sp中，以便于记住checkbox的状态
//	
//				}
			//});
		 
				
			}
		 
	
   /**
 * @return  根据checkbox判断item是否开启
 */
     public boolean isCheck(){
     	 return cb_chose.isChecked();	
		}
     public void setCheck(boolean isCheck){
    	 cb_chose.setChecked(isCheck);
    	 if(isCheck){
    		 tv_des.setText("自动更新已开启");
    	 }
    	 else{
    		 tv_des.setText("自动更新已关闭");
    	 }
    	 
     }
		 
			
	}




