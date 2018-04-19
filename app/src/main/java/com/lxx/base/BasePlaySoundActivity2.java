package com.lxx.base;

import com.lxx.util.PlaySoundPool;
import com.lxx.util.ShowToast;
import com.lxx.util.addPersonToDB;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnTouchListener;

/**
 * 播放声音的基类 数字是以减少的形式而变化的
 *
 * @author 李祥鑫 lxx
 * @CreateDate 2014-10-10 上午10:31:51
 */
public abstract class BasePlaySoundActivity2 extends Activity implements
		SensorEventListener {

	protected SensorManager mgr;
	protected Sensor proximity; // 距离传感器
	protected Vibrator vibrator; // 震动控件
	protected PlaySoundPool playSoundPool;

	private float lastVal = -1;
	protected int baseNum = 0;

	protected boolean Count = false; // 是否可以计数
	protected int max = 0; // 最大的数据

	protected boolean baseFlag = true; // 判断是否在能接触时增加数量

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(getLayoutId());

		initView();
		// 一直点亮屏幕
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

		this.mgr = (SensorManager) this.getSystemService(SENSOR_SERVICE);
		// 获取距离传感器
		this.proximity = this.mgr.getDefaultSensor(Sensor.TYPE_PROXIMITY);
		// 获取震动传感器 用来反应距离的变化
		this.vibrator = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);

		// 初始化声音元素
		playSoundPool = PlaySoundPool
				.initPlaySoundPool(getApplicationContext());

	}

	public abstract int getLayoutId();

	public abstract void initView();

	public abstract int getOnFullViewId();

	// 触发点击事件抽象函数
	public abstract void onTouchBaseEvent(View v, MotionEvent event, int num);

	// 添加触击的事件
	protected OnTouchListener baseOnTouchListener = new OnTouchListener() {
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			if (!Count) {
				return false;
			}

			if (v.getId() == getOnFullViewId()) {
				if (baseFlag) {
					baseNum--;
					playSoundNum();
				}
			}
			onTouchBaseEvent(v, event, baseNum);
			return false;
		}
	};

	// 当接近手机时的事件
	public abstract void comeInPhone(int num);

	// 判断是否是到数据100 true表示继续计时 false 表示退出
	public abstract void returnMenuActivity();

	@Override
	public void onSensorChanged(SensorEvent event) {
		if (!Count) {
			return;
		}
		// 目前的距离
		float thVal = event.values[0];
		if (this.lastVal == -1) {
			// 第一次进来
			this.lastVal = thVal;
		} else {
			if (thVal < this.lastVal) {
				baseFlag = false; // 标记接触是否能计数，当离开时表示能计数
				// 接近长振动
				this.vibrator.vibrate(200);
			} else {
				baseFlag = true; // 标记接触是否能计数，当离开时表示能计数
				// 离开短振动
				baseNum++;

				playSoundNum();

				comeInPhone(baseNum);

				// textView.setText(String.valueOf(num));

				this.vibrator.vibrate(100);
			}
			this.lastVal = thVal;
		}
		//
		// String msg = "Current val: " + this.lastVal;
		// System.out.println(msg);
	}

	/**
	 * 开始播放声音
	 *
	 * @author 李祥鑫 lxx
	 * @CreateDate 2014-10-13 上午11:49:43
	 *
	 */
	public void playSoundNum() {
		if (baseNum == 0) {
			exitAlert("您好厉害，目标已经完成，是否要保存呢?", 0, BasePlaySoundActivity2.this);
			baseNum = max;
		}

		switch (baseNum) {
			case 0:
				return;
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
			case 8:
			case 9:
				playSoundPool.play(baseNum, 0);
				break;
			case 10:
				playSoundPool.play3(10, 0, 1);
				break;
			case 11:
			case 12:
			case 13:
			case 14:
			case 15:
			case 16:
			case 17:
			case 18:
			case 19:
				playSoundPool.play5(baseNum % 10);
				break;
			case 20:
			case 30:
			case 40:
			case 50:
			case 60:
			case 70:
			case 80:
			case 90:
				playSoundPool.play6(baseNum / 10);
				break;
			default:
				playSoundPool.play7(baseNum / 10, baseNum % 10);
				break;
		}
	}

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {

	}

	// public abstract void init
	protected void onResume() {
		super.onResume();
		// 一定要在这注册
		this.mgr.registerListener(this, this.proximity,
				SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	protected void onPause() {
		super.onPause();
		// 一定要在这解注册
		this.mgr.unregisterListener(this, this.proximity);
	}

	// 显示对话框提示是否要存储数据
	// s == 1 表示退出 s == 0
	protected void exitAlert(String msg, final int s, Context context) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setMessage(msg)
				.setTitle("亲~")
				.setPositiveButton("嗯嗯", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						addPersonToDB addPersonToDB = new addPersonToDB(
								getApplicationContext());
						addPersonToDB.APTB(max);
						ShowToast
								.showNewsToast(getApplicationContext(), "保存成功");
						if (s == 1) {
							returnMenuActivity();
						} else {
							dialog.dismiss();
						}
					}
				})
				.setNegativeButton("算了", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						if (s == 1) {
							returnMenuActivity();
						}
						dialog.dismiss();
					}
				});
		AlertDialog alertDialog = builder.create();
		alertDialog.show();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			// System.out.println("onclick!");
			if (baseNum == 0) {
				returnMenuActivity();
			} else {
				exitAlert("需要保存这个记录么？ ", 1, BasePlaySoundActivity2.this);
			}
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		baseFlag = true;
	}

	@Override
	protected void onStop() {
		super.onStop();
		baseFlag = false;
	}

}
