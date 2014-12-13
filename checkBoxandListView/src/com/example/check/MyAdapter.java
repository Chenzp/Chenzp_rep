package com.example.check;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

public class MyAdapter<T> extends BaseAdapter {
	private List<T> mData = new ArrayList<T>();
	private Context mContext;
	private LayoutInflater mInflater;
	private Map<Integer, Boolean> mIsSelected = new HashMap<Integer, Boolean>();

	public MyAdapter(Context context, List<T> data) {
		this.mData = data;
		this.mContext = context;
		mInflater = LayoutInflater.from(context);
		init();
	}

	private void init() {
		for (int i = 0; i < mData.size(); i++) {
			mIsSelected.put(i, false);
		}
	}

	@Override
	public int getCount() {
		return mData.size();
	}

	@Override
	public Object getItem(int position) {
		return mData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if (null == convertView) {
			convertView = mInflater.inflate(R.layout.lv_item, null);
			viewHolder = new ViewHolder();
			viewHolder.tv_data = (TextView) convertView.findViewById(R.id.tv_data);
			viewHolder.cb = (CheckBox) convertView.findViewById(R.id.cb);
            viewHolder.bt = (Button) convertView.findViewById(R.id.bt);
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.tv_data.setText(mData.get(position).toString());
		viewHolder.cb.setChecked(mIsSelected.get(position));
        viewHolder.bt.setText(mData.get(position).toString());
        viewHolder.bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Toast.makeText(mContext,mData.get(position).toString(),Toast.LENGTH_SHORT).show();
            }
        });
		return convertView;
	}

	class ViewHolder {
		TextView tv_data;
		CheckBox cb;
        Button bt;
	}

	public Map<Integer, Boolean> getmIsSelected() {
		return mIsSelected;
	}
	

}
