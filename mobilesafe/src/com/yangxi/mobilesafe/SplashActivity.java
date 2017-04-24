package com.yangxi.mobilesafe;

import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.yangxi.mobilesafe.utils.DownLoadUtils;
import com.yangxi.mobilesafe.utils.getVersionInfoUtils;
import com.yangxi.mobilesafe.utils.streamTools;

import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class SplashActivity extends Activity {

	private TextView tv_version_name;// ��ʾ�汾���ƵĿؼ�
	private ProgressBar pb_splash;// �������صĽ�����
	private static final String TAG = "SplashActivity";
	private static final int MESSAGE_SHOW_DIALOG = 100;
	private static final int MESSAGE_ENTER_HOME = 101;
	private static final int OTHRT_EXCEPTION = 102;
     //��������ȡ����Ϣ
	private String nVersionurl;
	private String nVersionDesc;
	private String nVersionName;
	private int nVersionCode;
	
	//���ػ�ȡ����Ϣ
	private String mVersionName;
	private int mVersionCode;
	// ������Ϣhandler
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case MESSAGE_SHOW_DIALOG:
				// ����һ���Ի���
				showDailog();
				break;
			case MESSAGE_ENTER_HOME:
				// ����������
				enterHome();
				break;
			case OTHRT_EXCEPTION:
				// �����쳣����toast
				//enterHome();
				Toast.makeText(getApplicationContext(), "���Ժ�����", 0).show();
				break;
			}
		};

	};
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_splash);
		// ��ʼ���ؼ�
		initView();
		// ��ʼ������
		initData();
	}

	
	
	protected void enterHome() {
		 Intent intent = new Intent(this,HomeActivity.class);
		 startActivity(intent);
		 //������һ���µ�activity������ӭ����ر�
		 finish();
	}
	
	
	

	/**
	 * �����Ի�����߼�
	 */
	protected void showDailog() {
		// ����һ���Ի��򣬻�ȡ�Ի���Ĺ�����
		AlertDialog.Builder builder = new Builder(this);
		builder.setIcon(android.R.drawable.btn_default_small);
		builder.setTitle("�汾����");
		builder.setMessage(nVersionDesc);
		// ������������°�ťʱִ�еĴ���
		builder.setPositiveButton("��������",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// �����ȷ����ť��ʱ����������°汾
						// if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
			         String target = Environment.getExternalStorageDirectory().getAbsolutePath()
							 +File.separator+"mobilesafe.apk";
						DownLoadUtils down = new DownLoadUtils();
							//�����°汾
						down.downapk(nVersionurl, target);
						Toast.makeText(getApplicationContext(), "ִ�������صķ���",0).show();
					  
					}
				});
		builder.setNegativeButton("�Ժ���˵",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// ������Ժ����ִ�е��߼�
                           enterHome();
					}

				});
		builder.show();
	}
	
	
	
	/**
	 * ��ʼ�����ݣ����������ȡ���������ص�����
	 */
	private void initData() {
		
			new Thread(){
				public void run() {
					String path = "http://192.168.1.101:8080/updataInfo.json";
					//��¼��ʼִrun������ʱ���
					long start = System.currentTimeMillis();
					// ������Ϣ
					Message msg = Message.obtain();
					// ����URL����ָ��Ҫ���ʵķ�������ַ
					try {
						URL url = new URL(path);
						// ͨ��url�����ȡhttpurlconnection����
						HttpURLConnection urlconn = (HttpURLConnection) url
								.openConnection();
						// ������������ķ�ʽ
						urlconn.setRequestMethod("GET");
						//��������ʱ
						urlconn.setConnectTimeout(2000);
						// ���������ȡ��ʱʱ��
						urlconn.setReadTimeout(5000);
						// ��ȡ������������
						int code = urlconn.getResponseCode();
						if (code == 200) {
							// ��ȡ����������ʱ��
							InputStream is = urlconn.getInputStream();
							// ����ת��Ϊ�ַ���
							String temp = streamTools.streamToString(is);
							// ����json�ַ��������һ��������,��ȡ�������˵İ汾���ƣ��汾�ţ��Լ����ص�ַ���汾����
							Log.i(TAG, temp);
							JSONObject json = new JSONObject(temp);
							nVersionName = json.getString("versionName");
							nVersionDesc = json.getString("versionDesc");
							nVersionurl = json.getString("versionurl");
							nVersionCode = Integer.parseInt(json
									.getString("versionCode"));
							Log.i(TAG, nVersionName);
							Log.i(TAG, nVersionName);
							Log.i(TAG, nVersionDesc);
							Log.i(TAG, nVersionurl);
							is.close();
							// �������İ汾���뱾�ذ汾�����Ƚ�
							if (mVersionCode < nVersionCode) {
								// ������ʾ���µĶԻ���
								msg.what = MESSAGE_SHOW_DIALOG;
							} else {
								// ����������
								msg.what = MESSAGE_ENTER_HOME;
							}
						}
					} catch (Exception e) {
						msg.what = OTHRT_EXCEPTION;
						e.printStackTrace();
					} finally {
						//��¼ִ��run�����������ʱ���
					 long end = System.currentTimeMillis();
					 //�������ȡʱ��С��5�롣ǿ��˯�߹�5��
					 if((end - start) < 5000){
						 try {
							Thread.sleep(5000 - (end - start));
						} catch (Exception e) {
							e.printStackTrace();
						}
					 }
						handler.sendMessage(msg);
					}
				};
			}.start();
				 
	}

	// ��ʼ���ؼ�

	private void initView() {
		// ��ȡ���ذ汾����
		mVersionName = getVersionInfoUtils
				.getLocalVersionName(getApplicationContext());
		//��ȡ�汾��
		mVersionCode = getVersionInfoUtils
				.getLocalVersionCode(getApplicationContext());
		tv_version_name = (TextView) findViewById(R.id.tv_version_name);
		//�����صİ汾������ʾ��TextView �ؼ�����
		tv_version_name.setText("�汾���ƣ�" + mVersionName);
		pb_splash = (ProgressBar) findViewById(R.id.pb_splash);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.splash, menu);
		return true;
	}

}
