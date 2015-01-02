package com.example.snowball;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class DynamicStateActivity extends Activity {

	private TextView tv_content;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dynamic_state_item_content);

		tv_content=(TextView)this.findViewById(R.id.dynamic_state_item_content_tv);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
		Bundle bundle=getIntent().getExtras();
		
		tv_content.setText(bundle.getString("content"));		
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menucontent, menu);
		return super.onCreateOptionsMenu(menu);
	}

	/* Called whenever we call invalidateOptionsMenu() */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action buttons
		Intent intent = new Intent();
		switch (item.getItemId()) {
		case android.R.id.home: 
			finish();
            return true;  
		case R.id.action_home:
			intent.setClass(this, MainActivity.class);	
			startActivity(intent);
			finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}