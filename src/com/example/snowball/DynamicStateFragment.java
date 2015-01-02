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
			holder.dynamic_tv_device.setText("����"
					+ getData().get(position).get("device").toString() + "�ͻ���");
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
				content="�������ճ��������������Դ(17.380, 0.75, 4.51%)��Ϊ���һ�ܵ������Ĺ�Ʊ���ܵ���Ϊ15.42%,ͬ����֤��ָ������0.41%�����Դ��Ҫ��Ʒ�й������������ܱ����������ܵ�Դ�ȡ��ܵ������ҵ����������Ӱ�죬��˾�����°���ҵ�����ֲ��ѡ�10��14�գ���˾����ҵ��Ԥ����ʾ������ǰ�����ȹ�˾Ԥ��ʵ�־�����1.2��Ԫ~1.3��Ԫ��ͬ������Լ6.42%��15.28%��";
				break;
			case 1:
				device = "mac";
				content="ÿ��һ���𳵺ͷɻ���Ҫ�����˴���ͻ�����ͣ��Ϳ�ʼ���ġ������Ǹ������Ƿɻ����ṩ����ʳ��ֱ���Ƕ��в͵����衣�����Ƿ������棬�������޷��ԡ���˲��ֻ�����ȥ�����������û����ͣ��ڻһ���ʵ�ڶ���ͷ���ˣ����������˸���������̾Ͳ�Ѫ�ˡ�";
				break;
			case 2:
				device = "iphone";
				content="С�ֵ� �Ҳ�����㽨�� $����ҵ(SH600616)$ �㶼�� ������~";
				break;
			case 3:
				device = "ipad";
				content="��������";
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