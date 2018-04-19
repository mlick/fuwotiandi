package com.lxx.jlgy;

import com.lxx.util.ChangeFontManager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

public class WelcomeActivity extends Activity {

	private ImageView welcomeImageView;
	private SharedPreferences sharedPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		ChangeFontManager.changeFonts((ViewGroup) this.getWindow()
				.getDecorView(), this);
		if (AddUserName()) {
			StartAnimation();
		} else
			StartGetUserNameActivity();
	}

	private void StartGetUserNameActivity() {
		Intent intent = new Intent(WelcomeActivity.this,
				GetUserNameActivity.class);
		startActivity(intent);
		WelcomeActivity.this.finish();
	}

	public void StartAnimation() {
		welcomeImageView = (ImageView) findViewById(R.id.welcome_pic);
		AlphaAnimation animation = new AlphaAnimation(0.0f, 3.0f);
		animation.setDuration(1500);
		animation.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onAnimationEnd(Animation animation) {
				Intent intent = new Intent(WelcomeActivity.this,
						MenuActivity.class);
				startActivity(intent);
				WelcomeActivity.this.finish();
			}
		});

		welcomeImageView.setAnimation(animation);
		animation.start();
	}

	/**
	 *
	 * @author 李祥鑫 lxx
	 * @CreateDate 2014-4-10 上午8:34:48
	 * @return 如果之前没有返回true 否者返回false
	 *
	 */
	private boolean AddUserName() {
		sharedPreferences = getSharedPreferences("UserInfo",
				Context.MODE_PRIVATE);
		String userString = sharedPreferences.getString("UserName", "null");

		System.out.println("UserName  " + userString);

		if (userString.equals("null")) {
			return false;
		}
		return true;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			return false;
		}
		return super.onKeyDown(keyCode, event);
	}

}
