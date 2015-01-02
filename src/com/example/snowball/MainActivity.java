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

import android.app.ActionBar.OnNavigationListener;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

public class MainActivity extends Activity {
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	private Fragment fragment=null;
	
	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	private String[] mPlanetTitles;
	private int menuitem;
	private int height;
	private int width;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mTitle = mDrawerTitle = getTitle();
		mPlanetTitles = getResources().getStringArray(R.array.menu_array);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);

		WindowManager manager = (WindowManager)this.getSystemService(Context.WINDOW_SERVICE);       
        DisplayMetrics dm=new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(dm);
        width=dm.widthPixels;
        height=dm.heightPixels;
		// set a custom shadow that overlays the main content when the drawer
//		 opens
//		mDrawerLayout.setDrawerShadow(R.drawable.nav_icon_menu,
//				GravityCompat.START);
//		mDrawerLayout.setContentDescription("雪球");
		// set up the drawer's list view with items and click listener
		MyListAdapter myAdapter = new MyListAdapter(this, mPlanetTitles);
		mDrawerList.setAdapter(myAdapter);
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

		// enable ActionBar app icon to behave as action to toggle nav drawer
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
 

		// ActionBarDrawerToggle ties together the the proper interactions
		// between the sliding drawer and the action bar app icon
		mDrawerToggle = new ActionBarDrawerToggle(this, /* host Activity */
		mDrawerLayout, /* DrawerLayout object */
		R.drawable.nav_icon_menu, /* nav drawer image to replace 'Up' caret */
		R.string.drawer_open, /* "open drawer" description for accessibility */
		R.string.drawer_close /* "close drawer" description for accessibility */
		) {
			public void onDrawerClosed(View view) {
				 getActionBar().setTitle(mTitle);
				 actionStyle(menuitem);
			   	invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}

			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(mDrawerTitle);
				getActionBar().setNavigationMode(0);
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		if (savedInstanceState == null) {
			menuitem=1;
			selectItem(1);
			actionStyle(1);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		MenuItem searchItem = menu.findItem(R.id.action_websearch);  
	    SearchView searchView = (SearchView) searchItem.getActionView(); 
	    searchView.setQueryHint("搜索讨论/股票/用户/群组");
	    searchView.setQueryRefinementEnabled(false);
	    // 配置SearchView的属性
		return super.onCreateOptionsMenu(menu);
	}

	/* Called whenever we call invalidateOptionsMenu() */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// If the nav drawer is open, hide action items related to the content
		// view
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		if (menuitem == 1) {
			menu.findItem(R.id.action_websearch).setVisible(drawerOpen);
			menu.findItem(R.id.action_edit).setVisible(!drawerOpen);
			menu.findItem(R.id.action_friends).setVisible(false);
		}
		else if(menuitem==6){
			menu.findItem(R.id.action_websearch).setVisible(true);
			menu.findItem(R.id.action_edit).setVisible(false);
			menu.findItem(R.id.action_friends).setVisible(!drawerOpen);
			menu.findItem(R.id.action_friends).setIcon(R.drawable.icon_tool_more);
			}
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// The action bar home/up action should open or close the drawer.
		// ActionBarDrawerToggle will take care of this.
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		// Handle action buttons
		switch (item.getItemId()) {
		case R.id.action_websearch:
			// create intent to perform web search for this planet			
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/* The click listner for ListView in the navigation drawer */
	private class DrawerItemClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			menuitem=position;
			selectItem(position);
			actionStyle(menuitem);
		}
	}

	public class MyListAdapter extends BaseAdapter {
		private String[] strlist;
		private LayoutInflater mInflater;

		public MyListAdapter() {
		}

		public MyListAdapter(Context context, String[] mstrlist) {
			this.mInflater = LayoutInflater.from(context);
			this.strlist = mstrlist;
		}

		class ViewHolder {
			public LinearLayout ll;
			public ImageView iv;
			public TextView tv;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return strlist.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return strlist[position];
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(final int position, View convertView,
				ViewGroup parent) {
			// TODO Auto-generated method stub

			ViewHolder holder = null;
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.drawmenu_list_item, null);
			holder.ll = (LinearLayout) convertView
					.findViewById(R.id.drawmenu_ll_item);
			holder.iv = (ImageView) convertView
					.findViewById(R.id.drawmenu_iv_item);
			holder.tv = (TextView) convertView
					.findViewById(R.id.drawmenu_tv_item);

//			holder.iv.setBackgroundResource(R.drawable.refused_task);
			switch (position) {
			case 0:
				convertView = mInflater.inflate(
						R.layout.drawmenu_list_useritem, null);
				holder.ll = (LinearLayout) convertView
						.findViewById(R.id.drawmenu_ll_useritem);
				holder.iv = (ImageView) convertView
						.findViewById(R.id.drawmenu_iv_useritem);
				holder.tv = (TextView) convertView
						.findViewById(R.id.drawmenu_tv_useritem);

				holder.iv.setBackgroundResource(R.drawable.nine_task);

				break;
			case 1:
				holder.iv.setBackgroundResource(R.drawable.icon_menu_timeline);
				break;
			case 2:
				holder.iv.setBackgroundResource(R.drawable.icon_menu_stock);
				break;
			case 3:
				holder.iv.setBackgroundResource(R.drawable.icon_menu_message);
				break;
			case 4:
				holder.iv.setBackgroundResource(R.drawable.icon_menu_classic);
				break;
			case 5:
				holder.iv.setBackgroundResource(R.drawable.icon_menu_profit);
				break;
			case 6:
				holder.iv.setBackgroundResource(R.drawable.icon_menu_findpeople);
				break;
			case 7:
				holder.iv.setBackgroundResource(R.drawable.icon_menu_comment);
				break;
			case 8:
				holder.iv.setBackgroundResource(R.drawable.icon_menu_at);
				break;

			}
			holder.tv.setText(strlist[position].toString());
			if(position==8){
				holder.tv.setText("@"+strlist[position].toString());
			}
			return convertView;
		}
	}

	private void selectItem(int position) {
		// update the main content by replacing fragments
		mDrawerLayout.closeDrawer(mDrawerList);
//		Bundle args=null;
		fragment = new DynamicStateFragment();
		if(position==6){
			fragment = new SnowBallFriendsFragment(height,width);			
		}
//		args = new Bundle();
//		args.putInt(String.valueOf(position), position);
//		fragment.setArguments(args);
		mTitle=mPlanetTitles[position];
		switch(position){
		case 1:
			mTitle="";
			break;
		}

		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager.beginTransaction()
				.replace(R.id.content_frame, fragment).commit();
//		fragmentManager.;
		setTitle(mTitle);		
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(title);
	}

	/**
	 * When using the ActionBarDrawerToggle, you must call it during
	 * onPostCreate() and onConfigurationChanged()...
	 */

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	private void actionStyle(int menuitem){
		switch(menuitem){
		case 0:
			getActionBar().setNavigationMode(0);
			setTitle("我的");
			break;
		case 1:
			String[] arraylist=getResources().getStringArray(R.array.list_array);
//			SpinnerAdapter mSpinnerAdapter=ArrayAdapter.createFromResource(this,  
//	                R.array.list_array,  
//	                android.R.layout.simple_spinner_dropdown_item);  
			SpinnerAdapter mSpinnerAdapter = new SpinnerAdapter(getBaseContext(),arraylist); 
			getActionBar().setNavigationMode(getActionBar().NAVIGATION_MODE_LIST);//导航模式必须设为NAVIGATION_MODE_LIST  
//			getActionBar().set
			getActionBar().setListNavigationCallbacks(mSpinnerAdapter,  
			        mOnNavigationListener); 
			break;
		case 6:
			getActionBar().setNavigationMode(0);
			break;
		default:
			break;
		}
	}
	

	OnNavigationListener mOnNavigationListener = new OnNavigationListener() {  
		  
        @Override  
        public boolean onNavigationItemSelected(int position, long itemId) {  
//            Fragment newFragment = new DynamicStateFragment();
//            FragmentManager fragmentManager = getFragmentManager();
//    		fragmentManager.beginTransaction()
//    				.replace(R.id.content_frame, newFragment).commit();
             
            return true;  
        }  
    }; 
    
    public class SpinnerAdapter extends ArrayAdapter<String> {
    	private Context mContext;
    	private String[] mStringArray;

    	public SpinnerAdapter(Context context, String[] stringArray) {
    		super(context, android.R.layout.simple_spinner_item, stringArray);
    		mContext = context;
    		mStringArray = stringArray;   		
    	}
    	@Override
    	public View getDropDownView(int position, View convertView, ViewGroup parent) {
    		// 修改Spinner展开后的字体颜色
    		if (convertView == null) {
    			LayoutInflater inflater = LayoutInflater.from(mContext);
    			//我们也可以加载自己的Layout布局
    			convertView = inflater.inflate(
    					android.R.layout.simple_spinner_dropdown_item, parent,
    					false);
    			convertView.setBackgroundColor(getResources().getColor(R.color.lightwhite));
    		}
    		TextView tv = (TextView) convertView.findViewById(android.R.id.text1);
    		tv.setText(mStringArray[position]);
    		tv.setTextColor(getResources().getColor(R.color.black));
    		return convertView;

    	}
         
    	@Override
    	public View getView(int position, View convertView, ViewGroup parent) {
    		// 修改Spinner选择后结果的字体颜色
    		if (convertView == null) {
    			LayoutInflater inflater = LayoutInflater.from(mContext);
    			convertView = inflater.inflate(
    					android.R.layout.simple_spinner_item, parent, false);
//    			convertView.setBackgroundDrawable(getResources().getDrawable(R.drawable.nav_icon_more));
    		}
    		// 此处text1是Spinner系统的用来显示文字的TextView
    		TextView tv = (TextView) convertView.findViewById(android.R.id.text1);
//    		ImageView iv = (ImageView) convertView.findViewById(android.R.id.icon);
//    		iv.setBackgroundDrawable(getResources().getDrawable(R.drawable.nav_icon_more));
    		tv.setText(mStringArray[position]);
    		tv.setTextColor(getResources().getColor(R.color.white));	
    		return convertView;
    	}  	
    }
	/**
	 * Fragment that appears in the "content_frame", shows a planet
	 */
}