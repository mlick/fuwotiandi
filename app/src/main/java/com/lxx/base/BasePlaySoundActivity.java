package com.lxx.base;

import com.lxx.util.PlaySoundPool;
import com.lxx.util.ShowToast;
import com.lxx.util.UserUtils;
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
 * 所有操作共同的类 数字是以增加的形式而变化的
 *
 * @author 李祥鑫 lxx
 * @CreateDate 2014-10-10 上午10:31:51
 */
public abstract class BasePlaySoundActivity extends Activity implements
        SensorEventListener {

    protected SensorManager mgr;
    protected Sensor proximity; // 距离传感器
    protected Vibrator vibrator; // 震动控件
    protected PlaySoundPool playSoundPool;

    private float lastVal = 5;
    private int baseNum = 0, budanums = 0;

    public int getNum() {
        return baseNum;
    }

    public void setNum(int num) {
        this.baseNum = num;
    }

//	protected boolean baseIsFlag = true; // 判断是否在能接触时增加数量

    protected boolean isRunning = false; // 当前状态是否处于运动中

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
    protected View.OnClickListener baseOnTouchListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view.getId() == getOnFullViewId()) {
//                if (!isRunning) {
                budanums++;
                baseNum++;
                playSoundNum();
//                }
            }
            onTouchBaseEvent(view, null, baseNum);
        }
    };

    // 当接近手机时的事件
    public abstract void comeInPhone(int num);

    // 判断是否是到数据100 true表示继续计时 false 表示退出
    public abstract void returnMenuActivity();

    @Override
    public void onSensorChanged(SensorEvent event) {
//        System.out.println("====" + Arrays.toString(event.values));
        isRunning = true;
        // 目前的距离
        float thVal = event.values[0];

//        System.out.println(">>>>" + thVal + " " + this.lastVal);
        if (thVal != 0 && this.lastVal != thVal) {
            // 离开短振动
            baseNum++;

            playSoundNum();

            comeInPhone(baseNum);

            // textView.setText(String.valueOf(num));

            this.vibrator.vibrate(100);

            isRunning = false;
        }
        this.lastVal = thVal;
//
//        if (thVal == 0) {
//            // 开始进入
//
//        } else {
//            if (thVal < this.lastVal) {
//                isRunning = false; // 标记接触是否能计数，当离开时表示能计数
//                // 接近长振动
//                this.vibrator.vibrate(200);
//            } else {
//                isRunning = true; // 标记接触是否能计数，当离开时表示能计数
//                // 离开短振动
//                baseNum++;
//
//                playSoundNum();
//
//                comeInPhone(baseNum);
//
//                // textView.setText(String.valueOf(num));
//
//                this.vibrator.vibrate(100);
//            }
//            this.lastVal = thVal;
//        }
        //
        // String msg = "Current val: " + this.lastVal;
        // System.out.println(msg);
    }

    @Override
    public void onAccuracyChanged(Sensor arg0, int arg1) {
        System.out.println("BasePlaySoundActivity.onAccuracyChanged" + System.currentTimeMillis());
        System.out.println("arg0 = [" + arg0 + "], arg1 = [" + arg1 + "]");
    }


    /**
     * 开始播放声音
     *
     * @author 李祥鑫 lxx
     * @CreateDate 2014-10-13 上午11:49:43
     */
    public void playSoundNum() {
        if (baseNum == 100) {
            exitAlert("您好厉害，都已经做够100了，是否要保存呢?", 0, BasePlaySoundActivity.this);
            baseNum = 0;
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
//                        addPersonToDB addPersonToDB = new addPersonToDB(
//                                getApplicationContext());
//                        addPersonToDB.APTB(baseNum);

                        UserUtils.saveUserSportDate(baseNum, budanums, getTypeName());

                        ShowToast.showNewsToast(getApplicationContext(), "保存成功");

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
                exitAlert("需要保存这个记录么？ ", 1, BasePlaySoundActivity.this);
//                exitAlert("确定退出么？ ", 1, BasePlaySoundActivity.this);
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        isRunning = true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        isRunning = false;
    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        // 应用的最后一个Activity关闭时应释放DB
////        mgr.closeDB();
//
//        liteOrm.close();
//    }

    public abstract String getTypeName();

}
