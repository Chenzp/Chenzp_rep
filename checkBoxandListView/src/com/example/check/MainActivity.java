package com.example.check;

import java.util.ArrayList;
import java.util.List;

import com.example.check.MyAdapter.ViewHolder;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

	private Button bt_all;
	private Button bt_reverse;
	private Button bt_cancel;
	private ListView lv;
	private MyAdapter<String> myAdapter;
	private List<String> mData = new ArrayList<String>();

	private int mSelectNum = 0;
	private TextView tv_num;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
		setListener();

		for (int i = 1; i <= 100; i++) {
			mData.add("data" + i);
		}
		myAdapter = new MyAdapter<String>(this, mData);
		lv.setAdapter(myAdapter);

	}

	private void setListener() {
		bt_all.setOnClickListener(this);
		bt_cancel.setOnClickListener(this);
		bt_reverse.setOnClickListener(this);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				ViewHolder viewHolder = (ViewHolder) view.getTag();
				CheckBox cb = viewHolder.cb;
				cb.toggle();
				if (cb.isChecked()) {
					mSelectNum++;
				} else {
					mSelectNum--;
				}
				myAdapter.getmIsSelected().put(position, cb.isChecked());
				tv_num.setText(mSelectNum + "");
			}
		});
	}

	private void init() {
		bt_all = (Button) findViewById(R.id.bt_all);
		bt_reverse = (Button) findViewById(R.id.bt_reverse);
		bt_cancel = (Button) findViewById(R.id.bt_cancel);
		lv = (ListView) findViewById(R.id.lv);
		tv_num = (TextView) findViewById(R.id.tv_num);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_all:
			for (int i = 0; i < 100; i++) {
				if(!myAdapter.getmIsSelected().get(i)){
					myAdapter.getmIsSelected().put(i, true);
				}
			}
			mSelectNum = mData.size();
			dataChanged();
			break;
		case R.id.bt_reverse:
			for (int i = 0; i < 100; i++) {
				if(myAdapter.getmIsSelected().get(i)){
					myAdapter.getmIsSelected().put(i, false);
					mSelectNum--;
				}else{
					myAdapter.getmIsSelected().put(i, true);
					mSelectNum++;
				}
			}
			dataChanged();
			break;
		case R.id.bt_cancel:
			for(int i = 0;i<100;i++){
				if(myAdapter.getmIsSelected().get(i)){
					myAdapter.getmIsSelected().put(i, false);
				}
			}
			mSelectNum = 0;
			dataChanged();
			break;
		default:
			break;
		}
	}

	private void dataChanged() {
		myAdapter.notifyDataSetChanged();
		tv_num.setText(mSelectNum + "");
	}

}
