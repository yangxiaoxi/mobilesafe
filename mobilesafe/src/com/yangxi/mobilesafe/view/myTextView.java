package com.yangxi.mobilesafe.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

public class myTextView extends TextView {
    //��New��ʱ�����
	public myTextView(Context context) {
		super(context);
	}
  //ϵͳ���ã��������Լ���
	/**
	 * @param context������
	 * @param attrs���Լ���
	 */
	public myTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
//ϵͳ���ã��������Խ�Ϻ���ʽ
	/**
	 * @param context������
	 * @param attrs���Լ���
	 * @param defStyle��ʽ����
	 */
	public myTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	
	
	
	
	/* (non-Javadoc)
	 * @see android.view.View#isFocused()
	 */
	@Override
	public boolean isFocused() {
		return true;
	}

}
