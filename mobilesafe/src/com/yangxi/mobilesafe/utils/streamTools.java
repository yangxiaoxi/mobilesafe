package com.yangxi.mobilesafe.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import android.util.Log;

/**
 * @author 杨茜
 * 
 */

public class streamTools {

	/**
	 * @param is
	 *   服务器返回的输入流
	 * @return
	 *     将流转换成字符
	 */
	public static String streamToString(InputStream is) {
		try {
			//字节数组输出流，关闭无效
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			//设置每次读取的字节长度
			int len = 0;
			byte[] buffer = new byte[64];
			//读取输入流里面的信息，每次读1024个字节
			while((len = is.read(buffer))!=-1){
				//往临时缓冲不去里面写
				os.write(buffer,0,len);
			}
			//关闭流
			is.close();
			os.close();
			//获取临时缓冲区里面的字节
			byte[] result = os.toByteArray();
			//由于服务器使用的是iso-8859-1的编码方式，因为有汉字，所以这里的解析改为gbk来解析
			String temp = new  String(result,"gbk");
			return temp;
		} catch (Exception e) {
			e.printStackTrace();
			return "fault";
		}
	}

}
