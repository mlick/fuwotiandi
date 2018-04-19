package com.lxx.jlgy;

import com.lxx.util.ChangeFontManager;

import android.app.Activity;
import android.os.Bundle;
/*import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.view.View;*/
import android.view.KeyEvent;
import android.view.ViewGroup;

public class GuideActivity extends Activity {

	//private Movie mMovie;
	//private long mMovieStart;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(new CustomGifView(this));
		setContentView(R.layout.fragment_guide);
		ChangeFontManager.changeFonts((ViewGroup)this.getWindow().getDecorView(),this);
	}
	/*class CustomGifView extends View {
		public CustomGifView(Context context) {
			super(context);
			mMovie = Movie.decodeStream(getResources().openRawResource(
					R.drawable.animation));
		}
		public void onDraw(Canvas canvas) {
	            long now = android.os.SystemClock.uptimeMill(); 
	            
	            if (mMovieStart == 0) { // first time 
	                mMovieStart = now; 
	            } 
	            if (mMovie != null) { 
	                
	                int dur = mMovie.duration(); 
	                if (dur == 0) { 
	                    dur = 1000; 
	                } 
	                int relTime = (int) ((now-mMovieStart) % dur);                
	                mMovie.setTime(relTime); 
	                mMovie.draw(canvas, 0, 0); 
	                invalidate(); 
	            } 
	        }
	}*/
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			GuideActivity.this.setResult(RESULT_OK);
			GuideActivity.this.finish();
		}
		return super.onKeyDown(keyCode, event);
	}
}
