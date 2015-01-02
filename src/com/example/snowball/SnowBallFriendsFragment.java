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

import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SnowBallFriendsFragment extends Fragment {

	private ViewPager mPager;//页卡内容
    private List<View> listViews; // Tab页面列表
    private ImageView cursor;// 动画图片
    private TextView t1, t2, t3,t4;// 页卡头标
    private int offset = 0;// 动画图片偏移量
    private int currIndex = 0;// 当前页卡编号
    private int bmpW;// 动画图片宽度
    public int pagenum=0;
    LinearLayout.LayoutParams layoutParams;
    int screenW;
    int screenH;
    
    public View view1,view2,view3,view4;
	
	public SnowBallFriendsFragment() {
		// Empty constructor required for fragment subclasses
	}

	public SnowBallFriendsFragment(int height,int width) {
		// Empty constructor required for fragment subclasses
		this.screenH=height;
		this.screenW=width;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.snowball_friends, container,
				false);

		mPager = (ViewPager) rootView
				.findViewById(R.id.vPager);
		
		t1 = (TextView)rootView.findViewById(R.id.text1);
        t2 = (TextView)rootView.findViewById(R.id.text2);
        t3 = (TextView)rootView.findViewById(R.id.text3);
        t4 = (TextView)rootView.findViewById(R.id.text4);
        
        cursor=(ImageView)rootView.findViewById(R.id.cursor);
        
    	t1.setTextColor(getResources().getColor(R.color.blue_snowball));
    	t2.setTextColor(getResources().getColor(R.color.black));
    	t3.setTextColor(getResources().getColor(R.color.black));    	
    	
    	layoutParams = (LinearLayout.LayoutParams) cursor
				.getLayoutParams();
        
        Log.v("screenW",String.valueOf(screenW));
        
    	InitViewPager(inflater);   	
    	InitTextView();
		return rootView;
	}

	private void InitTextView() {
        t1.setOnClickListener(new MyOnClickListener(0));
        t2.setOnClickListener(new MyOnClickListener(1));
        t3.setOnClickListener(new MyOnClickListener(2));
        t4.setOnClickListener(new MyOnClickListener(3));
    }
	
	private void InitViewPager(LayoutInflater inflater) {
        listViews = new ArrayList<View>();

        view1 = inflater.inflate(R.layout.snowball_friends_focuseachother, null);        
        view2 = inflater.inflate(R.layout.snowball_friends_investment, null);       
        view3 = inflater.inflate(R.layout.snowball_friends_funs, null);       
        view4 = inflater.inflate(R.layout.snowball_friends_focus, null);
               
        ArrayList<View> viewList = new ArrayList<View>();// 将要分页显示的View装入数组中  
        viewList.add(view1);  
        viewList.add(view2);  
        viewList.add(view3);
        viewList.add(view4);  
               
        mPager.setAdapter(new MyPagerAdapter(viewList));
        mPager.setCurrentItem(0);
        MyOnPageChangeListener MyOnPageChange=new MyOnPageChangeListener();
        mPager.setOnPageChangeListener(MyOnPageChange);                  
    }
    
   
    public class MyOnClickListener implements View.OnClickListener {
        private int index = 0;

        public MyOnClickListener(int i) {
            index = i;
        }

        @Override
        public void onClick(View v) {       	
            mPager.setCurrentItem(index);
            TabChoose(index);
        }
    };
    
    public class MyPagerAdapter extends PagerAdapter {
        public List<View> mListViews;

        public MyPagerAdapter(List<View> mListViews) {
            this.mListViews = mListViews;
        }

        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView(mListViews.get(arg1));
        }

        @Override
        public void finishUpdate(View arg0) {
        }

        @Override
        public int getCount() {
            return mListViews.size();
        }

        @Override
        public Object instantiateItem(View arg0, int arg1) {
            ((ViewPager) arg0).addView(mListViews.get(arg1), 0);
            return mListViews.get(arg1);
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == (arg1);
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {
        }

        @Override
        public Parcelable saveState() {
            return null;
        }

        @Override
        public void startUpdate(View arg0) {
        }
    }
    
    
    public class MyOnPageChangeListener implements OnPageChangeListener {            
        //        @Override
        public void onPageSelected(int arg0) {           
            TabChoose(arg0);
            currIndex = arg0;
            cursor.setBackgroundColor(getResources().getColor(R.color.white));
//            layoutParams.leftMargin = arg0*screenW/4;
//            cursor.setLayoutParams(layoutParams);
        }

        public void onPageScrolled(int arg0, float arg1, int arg2) { 
        	layoutParams.leftMargin = (arg0*screenW+ arg2)/4;
        	cursor.setLayoutParams(layoutParams);
        }

        //        @Override
        public void onPageScrollStateChanged(int arg0) {
        }
    }
    private void TabChoose(int index) {
    	switch(index){
    	case 0:
        	t1.setTextColor(getResources().getColor(R.color.blue_snowball));
        	t2.setTextColor(getResources().getColor(R.color.black));
        	t3.setTextColor(getResources().getColor(R.color.black));
        	t4.setTextColor(getResources().getColor(R.color.black));
        	break;
        case 1:
        	t1.setTextColor(getResources().getColor(R.color.black));
        	t2.setTextColor(getResources().getColor(R.color.blue_snowball));
        	t3.setTextColor(getResources().getColor(R.color.black));
        	t4.setTextColor(getResources().getColor(R.color.black));
        	break;
        case 2:
        	t1.setTextColor(getResources().getColor(R.color.black));
        	t2.setTextColor(getResources().getColor(R.color.black));
        	t3.setTextColor(getResources().getColor(R.color.blue_snowball));
        	t4.setTextColor(getResources().getColor(R.color.black));
        	break;
        case 3:
        	t1.setTextColor(getResources().getColor(R.color.black));
        	t2.setTextColor(getResources().getColor(R.color.black));
        	t3.setTextColor(getResources().getColor(R.color.black));
        	t4.setTextColor(getResources().getColor(R.color.blue_snowball));
        	break;
    	}
        pagenum=index;
    }
}