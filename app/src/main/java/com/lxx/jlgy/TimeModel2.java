package com.lxx.jlgy;

//import com.lxx.util.ChangeFontManager;

import com.lxx.base.BasePlaySoundActivity;

import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TimeModel2 extends BasePlaySoundActivity {

    private TextView textView;
    private RelativeLayout re_full_onclick;
    /**
     * Called when the activity  first created.
     */

    private TextView second;

    private CheckBox check_modle;

    private int Second;

    // 创建一个Handler对象
    private Handler handler = new Handler();

    // 将要执行的操作写在线程对象的run方法当中
    private Runnable updateThread = new Runnable() {
        public void run() {
            Second = Integer.parseInt(second.getText().toString().trim());
            // 判断时间，并进行加减
            Second -= 1;

            // 设置文本框为显示时间
            second.setText(String.valueOf(Second));
            // 在run方法内部post或postDelayed方法
            // 延迟一秒之后将线程updateData加入到线程队列当中
            if (Second > 0) {
                handler.postDelayed(updateThread, 1000);
            } else if (Second == 0) {
                handler.removeCallbacks(updateThread);
                isRunning = false;
                second.setText("时间到！");
                Second = 60;
                exitAlert("需要保存这条记录么？", 0, TimeModel2.this);
            }
        }
    };

    // 返回到开始菜单
    public void returnMenuActivity() {
        handler.removeCallbacks(updateThread);
        TimeModel2.this.setResult(RESULT_OK);
        TimeModel2.this.finish();
    }

    @Override
    public String getTypeName() {
        return "限时模式";
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_time;

    }

    @Override
    public void initView() {
        textView = (TextView) findViewById(R.id.textview);

        re_full_onclick = (RelativeLayout) findViewById(R.id.re_full_onclick);
        re_full_onclick.setOnClickListener(baseOnTouchListener);

        second = (TextView) findViewById(R.id.s);
        check_modle = (CheckBox) findViewById(R.id.check_modle);

        isRunning = false;
        check_modle.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
                isRunning = arg1;

                if (arg1) {
                    handler.post(updateThread);
                } else {
                    handler.removeCallbacks(updateThread);
                }
            }
        });
    }

    @Override
    public int getOnFullViewId() {
        return R.id.re_full_onclick;
    }

    @Override
    public void onTouchBaseEvent(View v, MotionEvent event, int num) {
        textView.setText(String.valueOf(num));
    }

    @Override
    public void comeInPhone(int num) {
        textView.setText(String.valueOf(num));
    }
}
