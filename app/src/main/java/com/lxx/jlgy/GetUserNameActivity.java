package com.lxx.jlgy;

import com.lxx.util.ChangeFontManager;
import com.lxx.util.ShowToast;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


/**
 * 用户注册页面
 */
public class GetUserNameActivity extends Activity {

    private SharedPreferences sharedPreferences;
    private EditText editText;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.username_dialog);
        ChangeFontManager.changeFonts((ViewGroup) this.getWindow().getDecorView(), this);
        editText = (EditText) findViewById(R.id.edit_text);
        submitButton = (Button) findViewById(R.id.submit_info);

        submitButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                String userName = getUserName();

                if (userName.trim().equals("")) {
                    ShowToast.showNewsToast(getApplicationContext(), "输入的用户名不能为空");
                } else if (userName.trim().equals("null")) {
                    ShowToast.showNewsToast(getApplicationContext(), "输入的用户名不能为null");
                } else {
                    savaUserName(userName);
                }
            }
        });
    }

    //得到用户的名
    public String getUserName() {
        return editText.getText().toString();
    }

    public void savaUserName(String userName) {
        sharedPreferences = getSharedPreferences("UserInfo",
                Context.MODE_PRIVATE);
        Editor editor = sharedPreferences.edit();
        editor.putString("UserName", userName);
        editor.commit();

        startMenuActivity();
    }

    private void startMenuActivity() {
        Intent intent = new Intent(GetUserNameActivity.this, MenuActivity.class);
        startActivity(intent);
        GetUserNameActivity.this.finish();
    }
}
