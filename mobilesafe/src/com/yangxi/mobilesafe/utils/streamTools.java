package com.yangxi.mobilesafe.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import android.util.Log;

/**
 * @author ����
 * 
 */

public class streamTools {

	/**
	 * @param is
	 *   ���������ص�������
	 * @return
	 *     ����ת�����ַ�
	 */
	public static String streamToString(InputStream is) {
		try {
			//�ֽ�������������ر���Ч
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			//����ÿ�ζ�ȡ���ֽڳ���
			int len = 0;
			byte[] buffer = new byte[64];
			//��ȡ�������������Ϣ��ÿ�ζ�1024���ֽ�
			while((len = is.read(buffer))!=-1){
				//����ʱ���岻ȥ����д
				os.write(buffer,0,len);
			}
			//�ر���
			is.close();
			os.close();
			//��ȡ��ʱ������������ֽ�
			byte[] result = os.toByteArray();
			//���ڷ�����ʹ�õ���iso-8859-1�ı��뷽ʽ����Ϊ�к��֣���������Ľ�����Ϊgbk������
			String temp = new  String(result,"gbk");
			return temp;
		} catch (Exception e) {
			e.printStackTrace();
			return "fault";
		}
	}

}
