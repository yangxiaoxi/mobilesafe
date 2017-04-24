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

	private TextView tv_version_name;// 显示版本名称的控件
	private ProgressBar pb_splash;// 用来加载的进度条
	private static final String TAG = "SplashActivity";
	private static final int MESSAGE_SHOW_DIALOG = 100;
	private static final int MESSAGE_ENTER_HOME = 101;
	private static final int OTHRT_EXCEPTION = 102;
     //服务器获取的信息
	private String nVersionurl;
	private String nVersionDesc;
	private String nVersionName;
	private int nVersionCode;
	
	//本地获取的信息
	private String mVersionName;
	private int mVersionCode;
	// 创建消息handler
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case MESSAGE_SHOW_DIALOG:
				// 弹出一个对话框
				showDailog();
				break;
			case MESSAGE_ENTER_HOME:
				// 进入主界面
				enterHome();
				break;
			case OTHRT_EXCEPTION:
				// 出现异常弹出toast
				//enterHome();
				Toast.makeText(getApplicationContext(), "请稍后再试", 0).show();
				break;
			}
		};

	};
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_splash);
		// 初始化控件
		initView();
		// 初始化数据
		initData();
	}

	
	
	protected void enterHome() {
		 Intent intent = new Intent(this,HomeActivity.class);
		 startActivity(intent);
		 //当开启一个新的activity，将欢迎界面关闭
		 finish();
	}
	
	
	

	/**
	 * 弹出对话框的逻辑
	 */
	protected void showDailog() {
		// 弹出一个对话框，获取对话框的构建器
		AlertDialog.Builder builder = new Builder(this);
		builder.setIcon(android.R.drawable.btn_default_small);
		builder.setTitle("版本更新");
		builder.setMessage(nVersionDesc);
		// 当点击立即更新按钮时执行的代码
		builder.setPositiveButton("立即更新",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// 当点击确定按钮的时候进入下载新版本
						// if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
			         String target = Environment.getExternalStorageDirectory().getAbsolutePath()
							 +File.separator+"mobilesafe.apk";
						DownLoadUtils down = new DownLoadUtils();
							//下载新版本
						down.downapk(nVersionurl, target);
						Toast.makeText(getApplicationContext(), "执行了下载的方法",0).show();
					  
					}
				});
		builder.setNegativeButton("稍后再说",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// 当点击稍后更新执行的逻辑
                           enterHome();
					}

				});
		builder.show();
	}
	
	
	
	/**
	 * 初始化数据，连接网络获取服务器返回的数据
	 */
	private void initData() {
		
			new Thread(){
				public void run() {
					String path = "http://192.168.1.101:8080/updataInfo.json";
					//记录开始执run方法的时间戳
					long start = System.currentTimeMillis();
					// 创建消息
					Message msg = Message.obtain();
					// 创建URL对象，指定要访问的服务器地址
					try {
						URL url = new URL(path);
						// 通过url对象获取httpurlconnection对象
						HttpURLConnection urlconn = (HttpURLConnection) url
								.openConnection();
						// 设置连接网络的方式
						urlconn.setRequestMethod("GET");
						//设置请求超时
						urlconn.setConnectTimeout(2000);
						// 设置网络读取超时时间
						urlconn.setReadTimeout(5000);
						// 获取服务器返回码
						int code = urlconn.getResponseCode();
						if (code == 200) {
							// 获取服务器返回时流
							InputStream is = urlconn.getInputStream();
							// 将流转化为字符串
							String temp = streamTools.streamToString(is);
							// 解析json字符串，抽成一个工具类,获取服务器端的版本名称，版本号，以及下载地址，版本描述
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
							// 服务器的版本号与本地版本号做比较
							if (mVersionCode < nVersionCode) {
								// 弹出提示更新的对话框
								msg.what = MESSAGE_SHOW_DIALOG;
							} else {
								// 进入主界面
								msg.what = MESSAGE_ENTER_HOME;
							}
						}
					} catch (Exception e) {
						msg.what = OTHRT_EXCEPTION;
						e.printStackTrace();
					} finally {
						//记录执行run方法结束后的时间戳
					 long end = System.currentTimeMillis();
					 //当网络读取时间小于5秒。强制睡眠够5秒
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

	// 初始化控件

	private void initView() {
		// 获取本地版本名称
		mVersionName = getVersionInfoUtils
				.getLocalVersionName(getApplicationContext());
		//获取版本号
		mVersionCode = getVersionInfoUtils
				.getLocalVersionCode(getApplicationContext());
		tv_version_name = (TextView) findViewById(R.id.tv_version_name);
		//将本地的版本名称显示在TextView 控件上面
		tv_version_name.setText("版本名称：" + mVersionName);
		pb_splash = (ProgressBar) findViewById(R.id.pb_splash);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.splash, menu);
		return true;
	}

}
