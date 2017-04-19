package com.yangxi.mobilesafe;

import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

import com.yangxi.mobilesafe.utils.DownLoadUtils;
import com.yangxi.mobilesafe.utils.getVersionCode;
import com.yangxi.mobilesafe.utils.getVersionName;
import com.yangxi.mobilesafe.utils.streamTools;

import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
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
	private int mVersionCode;// ���ذ汾��
	private String mVersionName;// ���ذ汾����
	private int wVersionCode;// �������汾��
	private static final String TAG = "SplashActivity";
	private static final int MESSAGE_SHOW_DIALOG = 100;
	private static final int MESSAGE_ENTER_HOME = 101;
	private static final int OTHRT_EXCEPTION = 102;

	private String wVersionurl;
	private String wVersionDesc;
	private String wVersionName;
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

	// �����Ի�����߼�
	protected void showDailog() {
		// ����һ���Ի��򣬻�ȡ�Ի���Ĺ�����
		AlertDialog.Builder builder = new Builder(this);
		builder.setIcon(R.drawable.icon);
		builder.setTitle("�汾����");
		// ������������°�ťʱִ�еĴ���
		builder.setPositiveButton("��������",
				new DialogInterface.OnClickListener() {

					private String path;

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// �����ȷ����ť��ʱ��������ذ�װ�°汾
						 if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
						 {
							path = Environment.getExternalStorageDirectory().getAbsolutePath()
									 +File.separator+"mobilesafe.apk";
						 }
						DownLoadUtils down = new DownLoadUtils();
						down.downapk(wVersionurl, path);
					}
				});
		builder.setNegativeButton("�Ժ����",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// ������Ժ����ִ�е��߼�

					}

				});
	}

	/**
	 * ��ʼ�����ݣ����������ȡ���������ص�����
	 */
	private void initData() {
		new Thread() {

			// ���������ȡ��������json�ļ�
			public void run() {
				String path = "http://192.168.1.101:8080/updataInfo.html";
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
					// �������糬ʱʱ��
					urlconn.setReadTimeout(5000);
					// ��ȡ������������
					int code = urlconn.getResponseCode();
					if (code == 200) {
						// ��ȡ����������ʱ��
						InputStream is = urlconn.getInputStream();
						// ����ת��Ϊ�ַ���
						String temp = streamTools.streamToString(is);
						Log.i(TAG, temp);
						// ����json�ַ��������һ��������,��ȡ�������˵İ汾���ƣ��汾�ţ��Լ����ص�ַ���汾����
						JSONObject json = new JSONObject(temp);
						wVersionName = json.getString("versionName");
						wVersionDesc = json.getString("versionDesc");
						wVersionurl = json.getString("versionurl");
						wVersionCode = Integer.parseInt(json
								.getString("versionCode"));
						is.close();
						// �������İ汾���뱾�ذ汾�����Ƚ�
						if (mVersionCode < wVersionCode) {
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
					handler.sendMessage(msg);
				}
			};
		}.start();

	}

	// ��ʼ���ؼ�

	private void initView() {
		// ��ȡ���ذ汾����
		mVersionName = getVersionName
				.getLocalVersionName(getApplicationContext());
		// ��ȡ���ذ汾��
		mVersionCode = getVersionCode
				.getLocalVersionCode(getApplicationContext());
		tv_version_name = (TextView) findViewById(R.id.tv_version_name);
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
