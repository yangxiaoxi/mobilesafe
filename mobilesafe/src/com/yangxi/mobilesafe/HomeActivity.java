package com.yangxi.mobilesafe;

import com.yangxi.mobilesafe.utils.sharePraferenceUtil;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends Activity {
	GridView mgv_home;
	sharePraferenceUtil sp;
	// 舒适化数据
	private String[] title = { "手机防盗", "通信卫士", "软件管理", "进程管理 ", "流量统计", "手机杀毒",
			"缓存清理", "高级工具", "设置中心" };
	private int[] image = { R.drawable.first, R.drawable.second,
			R.drawable.third, R.drawable.four, R.drawable.five, R.drawable.six,
			R.drawable.seven, R.drawable.eight, R.drawable.nine };
	private EditText et_setpsw;
	private EditText et_confirmpsw;
	private String secound_password;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		initView();
	}

	/**
	 * 初始化控件
	 */
	private void initView() {
		mgv_home = (GridView) findViewById(R.id.gv_home);
		myAdapter adapter = new myAdapter();
		mgv_home.setAdapter(adapter);
		// 给item设置点击事件
		mgv_home.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				switch (position) {
				case 0:
					// 当点击手机防盗按钮时，弹出对话框
					showDialog();
					// enterSafeActivity();
					break;
				case 1:
					break;
				case 2:
					break;
				case 3:
					break;
				case 4:
					break;
				case 5:
					break;
				case 6:
					break;
				case 7:
					break;
				case 8:
					try {
						enterSettingActivity();
					} catch (Exception e) {
						enterSettingActivity();
					}
					break;

				}

			}

		});
	}

	protected void showDialog() {
		
		// 判断sp里面是否保存了密码，如果保存了则是第二次
		sp = new sharePraferenceUtil();
		final String password = sp.getString(getApplicationContext());
		if (TextUtils.isEmpty(password)) {
			// 当密码为空时，则是第一次点击弹出设置密码对话框
			Builder builder = new AlertDialog.Builder(this);
			final AlertDialog setDialog = builder.create();
			// 将要显示的对话框对应的布局文件转换成一个view
			View view = View.inflate(getApplicationContext(),
					R.layout.safe_activity_showdialog, null);
			setDialog.setView(view);
			setDialog.show();
			et_setpsw = (EditText) view.findViewById(R.id.et_setpsw);
			et_confirmpsw = (EditText) view.findViewById(R.id.et_confirmpsw);
			Button bt_confirm = (Button)view. findViewById(R.id.bt_confirm);
			Button bt_cancle = (Button)view. findViewById(R.id.bt_cancle);
			//确认按钮的点击事件
			bt_confirm.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					String setpsw = et_setpsw.getText().toString();
					sp.putString(getApplicationContext(), setpsw);
					enterSafeActivity();
					setDialog.dismiss();
				}
			});
			//点击取消点击事件
			bt_cancle.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					//点击取消对话框
					setDialog.dismiss();
				}
			});

		} else {
			// 当密码不为空时，取出密码与输入密码核对。第二次点击时弹出确认密码对话框
			Builder builder = new AlertDialog.Builder(this);
			final AlertDialog setDialog = builder.create();
			// 将要显示的对话框对应的布局文件转换成一个view
			View view = View.inflate(getApplicationContext(),
					R.layout.safe_activity_confirmdialog, null);
			Button bt_secound_confirm  = (Button) view.findViewById(R.id.bt_secound_confirm);
			Button bt_secound_cancle = (Button) view.findViewById(R.id.bt_secound_cancle);
			EditText et_secound_confirmpsw = (EditText) view.findViewById(R.id.et_secound_confirmpsw);
			secound_password = et_secound_confirmpsw.getText().toString();
			bt_secound_confirm.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					if (secound_password.equals(password)) {
						// 如果输入的确认密码和第一次设置的密码一样，就进入手机防盗界面
						enterSafeActivity();
						setDialog.dismiss();
					} else {
						Toast.makeText(getApplicationContext(), "密码错误", 0).show();
						setDialog.dismiss();
					}
					
				}
			});
			
			bt_secound_cancle.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					setDialog.dismiss();
				}
			});
			
		}

	}

	protected void enterSafeActivity() {
		Intent intent = new Intent();
		// 因为是默认的所以系统会自动添加该过滤器
		intent.addCategory("android.intent.category.DEFAULT");
		intent.setAction("SafeActivity");
		startActivity(intent);

	}

	public void enterSettingActivity() {
		Intent intent = new Intent();
		intent.setAction("SettingActivity");
		// intent.addCategory(String category);
		startActivity(intent);

	}

	class myAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return title.length;
		}

		@Override
		public Object getItem(int position) {

			return title[position];
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = View.inflate(getApplicationContext(),
					R.layout.home_grid_item, null);
			ImageView iv_home_grid_image = (ImageView) view
					.findViewById(R.id.iv_home_grid_image);
			iv_home_grid_image.setBackgroundResource(image[position]);
			TextView tv_home_grid_title = (TextView) view
					.findViewById(R.id.tv_home_grid_title);
			tv_home_grid_title.setText(title[position]);
			return view;
		}

	}
	
	@Override
	protected void onStop() {
		super.onStop();
	}

}
