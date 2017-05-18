package com.yangxi.mobilesafe;

import android.app.Activity;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

public class HomeActivity extends Activity {
	GridView mgv_home;
	// 舒适化数据
	private String[] title = { "手机防盗", "通信卫士", "软件管理", "进程管理 ", "流量统计", "手机杀毒",
			"缓存清理", "高级工具", "设置中心" };
	private int[] image = { R.drawable.first, R.drawable.second,
			R.drawable.third, R.drawable.four, R.drawable.five, R.drawable.six,
			R.drawable.seven, R.drawable.eight, R.drawable.nine };

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
					enterSettingActivity();
					break;

				}

			}

			
		});
	}
	public void enterSettingActivity() {
		Intent intent = new Intent();
		intent.setAction("SettingActivity");
		//intent.addCategory(String category);
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

}
