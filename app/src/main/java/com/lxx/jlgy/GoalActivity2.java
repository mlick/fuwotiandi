package com.lxx.jlgy;

//import com.lxx.util.ChangeFontManager;

import com.lxx.base.BasePlaySoundActivity2;
import com.lxx.util.ShowToast;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class GoalActivity2 extends BasePlaySoundActivity2 {

    // private int k = 1;
    private TextView textView;
    // EditText editText;
    private EditText editText;
    private RelativeLayout re_goal_onclick;
    // num=20;
    private Button sureButton;

    private InputMethodManager imm;

    // 显示输入数据的对话框
    protected void showEditView() {
        editText.setVisibility(View.VISIBLE);
        sureButton.setVisibility(View.VISIBLE);
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_goal;
    }

    @Override
    public void initView() {
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        textView = (TextView) findViewById(R.id.textview1);
        editText = (EditText) findViewById(R.id.edittext);

        sureButton = (Button) findViewById(R.id.sure_button);
        sureButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String getString = editText.getText().toString().trim();
                if (getString.equals("")) {
                    ShowToast.showNewsToast(getApplicationContext(),
                            "请先输入一个数字！");
                    return;
                }
                int num = Integer.parseInt(getString);
                if (num >= 100) {
                    ShowToast.showNewsToast(getApplicationContext(),
                            "请输入100以内的数！");
                    return;
                }
                baseNum = num;
                max = num;

                Count = true;
                imm = (InputMethodManager) editText.getContext()
                        .getSystemService(Context.INPUT_METHOD_SERVICE);

                if (imm.isActive()) { // 强制关闭输入法的键盘
                    // imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT,
                    // InputMethodManager.HIDE_NOT_ALWAYS); //
                    // 关闭软键盘，开启方法相同，这个方法是切换开启与关闭状态的
                    imm.hideSoftInputFromWindow(
                            editText.getApplicationWindowToken(), 0);
                }

                // editText.setText("请输入1~100数字");
                textView.setText(String.valueOf(num));
                editText.setVisibility(View.GONE);
                sureButton.setVisibility(View.GONE);
                textView.setVisibility(View.VISIBLE);
            }
        });

        re_goal_onclick = (RelativeLayout) findViewById(R.id.re_goal_onclick);
        re_goal_onclick.setOnTouchListener(baseOnTouchListener);
    }

    @Override
    public int getOnFullViewId() {
        return R.id.re_goal_onclick;
    }

    @Override
    public void onTouchBaseEvent(View v, MotionEvent event, int num) {
        textView.setText(String.valueOf(num));
    }

    @Override
    public void comeInPhone(int num) {
        textView.setText(String.valueOf(num));
    }

    // 返回到开始菜单
    public void returnMenuActivity() {
        GoalActivity2.this.setResult(RESULT_OK);
        GoalActivity2.this.finish();
    }

}
