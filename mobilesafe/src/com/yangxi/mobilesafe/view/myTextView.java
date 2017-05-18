package com.yangxi.mobilesafe.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

public class myTextView extends TextView {
    //当New的时候调用
	public myTextView(Context context) {
		super(context);
	}
  //系统调用，加上属性集合
	/**
	 * @param context上下文
	 * @param attrs属性集合
	 */
	public myTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
//系统调用，加上属性结合和样式
	/**
	 * @param context上下文
	 * @param attrs属性集合
	 * @param defStyle样式集合
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
