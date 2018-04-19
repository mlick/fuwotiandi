package com.lxx.jlgy;

import com.lxx.base.BasePlaySoundActivity;

import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 最新改版的新版的模式
 *
 * @author 李祥鑫 lxx
 * @CreateDate 2014-10-17 下午12:22:11
 */
public class FreeModel3 extends BasePlaySoundActivity {

    private TextView textView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public int getOnFullViewId() {
        return R.id.re_full_onclick;
    }

    @Override
    public void initView() {
        textView = (TextView) findViewById(R.id.textview);
        findViewById(R.id.re_full_onclick).setOnClickListener(baseOnTouchListener);
    }

    @Override
    public void onTouchBaseEvent(View v, MotionEvent event, int num) {
        textView.setText(String.valueOf(num));
    }

    @Override
    public void comeInPhone(int num) {
        textView.setText(String.valueOf(num));
    }

    @Override
    public void returnMenuActivity() {
        FreeModel3.this.finish();
    }

    @Override
    public String getTypeName() {
        return "自由模式";
    }

}
