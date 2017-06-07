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
	// ���ʻ�����
	private String[] title = { "�ֻ�����", "ͨ����ʿ", "�������", "���̹��� ", "����ͳ��", "�ֻ�ɱ��",
			"��������", "�߼�����", "��������" };
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
	 * ��ʼ���ؼ�
	 */
	private void initView() {
		mgv_home = (GridView) findViewById(R.id.gv_home);
		myAdapter adapter = new myAdapter();
		mgv_home.setAdapter(adapter);
		// ��item���õ���¼�
		mgv_home.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				switch (position) {
				case 0:
					// ������ֻ�������ťʱ�������Ի���
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
		
		// �ж�sp�����Ƿ񱣴������룬������������ǵڶ���
		sp = new sharePraferenceUtil();
		final String password = sp.getString(getApplicationContext());
		if (TextUtils.isEmpty(password)) {
			// ������Ϊ��ʱ�����ǵ�һ�ε��������������Ի���
			Builder builder = new AlertDialog.Builder(this);
			final AlertDialog setDialog = builder.create();
			// ��Ҫ��ʾ�ĶԻ����Ӧ�Ĳ����ļ�ת����һ��view
			View view = View.inflate(getApplicationContext(),
					R.layout.safe_activity_showdialog, null);
			setDialog.setView(view);
			setDialog.show();
			et_setpsw = (EditText) view.findViewById(R.id.et_setpsw);
			et_confirmpsw = (EditText) view.findViewById(R.id.et_confirmpsw);
			Button bt_confirm = (Button)view. findViewById(R.id.bt_confirm);
			Button bt_cancle = (Button)view. findViewById(R.id.bt_cancle);
			//ȷ�ϰ�ť�ĵ���¼�
			bt_confirm.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					String setpsw = et_setpsw.getText().toString();
					sp.putString(getApplicationContext(), setpsw);
					enterSafeActivity();
					setDialog.dismiss();
				}
			});
			//���ȡ������¼�
			bt_cancle.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					//���ȡ���Ի���
					setDialog.dismiss();
				}
			});

		} else {
			// �����벻Ϊ��ʱ��ȡ����������������˶ԡ��ڶ��ε��ʱ����ȷ������Ի���
			Builder builder = new AlertDialog.Builder(this);
			final AlertDialog setDialog = builder.create();
			// ��Ҫ��ʾ�ĶԻ����Ӧ�Ĳ����ļ�ת����һ��view
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
						// ��������ȷ������͵�һ�����õ�����һ�����ͽ����ֻ���������
						enterSafeActivity();
						setDialog.dismiss();
					} else {
						Toast.makeText(getApplicationContext(), "�������", 0).show();
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
		// ��Ϊ��Ĭ�ϵ�����ϵͳ���Զ���Ӹù�����
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
