/*
 * Copyright 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.snowball;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class DynamicStateFragment extends Fragment {

	public DynamicStateFragment() {
		// Empty constructor required for fragment subclasses
	}

	public DynamicStateFragment(String str) {
		// Empty constructor required for fragment subclasses
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.dynamic_state, container,
				false);

		ListView dynamicstate = (ListView) rootView
				.findViewById(R.id.dynamic_state_lv);
		MyAdapter mAdapter = new MyAdapter(inflater);
		dynamicstate.setAdapter(mAdapter);
		mAdapter.notifyDataSetChanged();
		return rootView;
	}

	class MyAdapter extends BaseAdapter {
		private LayoutInflater mInflater;

		class ViewHolder {
			public LinearLayout dynamic_ll;
			public ImageView dynamic_iv_picture;
			public TextView dynamic_tv_name;
			public TextView dynamic_tv_device;
			public TextView dynamic_tv_updatetime;
			public TextView dynamic_tv_content;

		}

		public MyAdapter(LayoutInflater inflater) {
			this.mInflater = inflater;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return getData().size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return getData().get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			ViewHolder holder = null;
			if (convertView == null) {

				holder = new ViewHolder();
				convertView = mInflater.inflate(R.layout.dynamic_state_item,
						null);
				holder.dynamic_ll = (LinearLayout) convertView
						.findViewById(R.id.dynamic_state_ll_item);

				holder.dynamic_tv_name = (TextView) convertView
						.findViewById(R.id.dynamic_state_item_header_tv_name);
				holder.dynamic_tv_device = (TextView) convertView
						.findViewById(R.id.dynamic_state_item_header_tv_device);
				holder.dynamic_tv_updatetime = (TextView) convertView
						.findViewById(R.id.dynamic_state_item_header_tv_updatetime);
				holder.dynamic_tv_content = (TextView) convertView
						.findViewById(R.id.dynamic_state_item_content);
				convertView.setTag(holder);

			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			holder.dynamic_tv_name.setText(getData().get(position).get("name")
					.toString());
			holder.dynamic_tv_device.setText("来自"
					+ getData().get(position).get("device").toString() + "客户端");
			holder.dynamic_tv_updatetime.setText(getData().get(position)
					.get("updatetime").toString());
			holder.dynamic_tv_content.setText(getData().get(position)
					.get("content").toString());

			holder.dynamic_tv_content.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent=new Intent();
					Bundle bundle=new Bundle();
					bundle.putString("content", getData().get(position)
					.get("content").toString());
					intent.setClass(getActivity(), DynamicStateActivity.class);
					intent.putExtras(bundle);  
					getActivity().startActivity(intent);
				}
				
			});
			return convertView;
		}
	}

	public ArrayList<HashMap<String, Object>> getData() {
		ArrayList<HashMap<String, Object>> result = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < 20; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			String name = "herve" + i * 10;
			String device = "";
			String updatetime = "";
			String content="";
			int remainder = i % 4;
			switch (remainder) {
			case 0:
				device = "android";
				content="因连续收出六连阴，阳光电源(17.380, 0.75, 4.51%)成为最近一周跌幅最大的股票，周跌幅为15.42%,同期上证综指则上涨0.41%阳光电源主要产品有光伏逆变器、风能变流器、储能电源等。受到光伏产业遭遇寒冬的影响，公司今年下半年业绩表现不佳。10月14日，公司发布业绩预告显示，今年前三季度公司预计实现净利润1.2亿元~1.3亿元，同比增长约6.42%～15.28%。";
				break;
			case 1:
				device = "mac";
				content="每次一坐火车和飞机，要是忘了带午餐或者晚餐，就开始糟心。不管是高铁还是飞机，提供的主食简直就是对中餐的侮辱。无论是饭还是面，几乎就无法吃。配菜部分还过得去。今天出门又没带晚餐，在灰机上实在饿得头晕了，出机场买了个卷饼，立刻就补血了。";
				break;
			case 2:
				device = "iphone";
				content="小兄弟 我不想给你建议 $金枫酒业(SH600616)$ 你都看 无语了~";
				break;
			case 3:
				device = "ipad";
				content="完美告终";
				break;
			}
			Date current = new Date();
			current.setDate(current.getDate() - i);
			current.setHours(current.getHours() - i);
			SimpleDateFormat sDateFormat = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			String date = sDateFormat.format(current);
			updatetime = date.toString().substring(5, 16);
			map.put("name", name);
			map.put("device", device);
			map.put("updatetime", updatetime);
			map.put("content", content);

			result.add(map);
		}
		return result;
	}
}