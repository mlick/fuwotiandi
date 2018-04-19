package com.lxx.jlgy;

import com.lxx.jlgy.db.DBHelper;
import com.lxx.util.ChangeFontManager;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class AboutMeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		DBHelper dbHelper = new DBHelper(getApplicationContext());
		dbHelper.s = "";
		
		setContentView(R.layout.activity_about_me);
		ChangeFontManager.changeFonts((ViewGroup)this.getWindow().getDecorView(),this);
		ImageButton imageButton = (ImageButton) findViewById(R.id.backButton);
		imageButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				AboutMeActivity.this.setResult(RESULT_OK);
				AboutMeActivity.this.finish();
			}
		});
	}
}
