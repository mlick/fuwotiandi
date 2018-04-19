package com.lxx.jlgy;

import com.lxx.util.ChangeFontManager;
import com.lxx.util.UserUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MenuActivity extends Activity {

    private Button freeButton;
    private Button mubiaoButton;
    private Button xianshiButton;
    private Button lookButton;
    private Button guideButton;
    private Button exitButton;
    private LinearLayout mainLayout;

    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_menu);

        ((TextView) findViewById(R.id.user_name)).setText(UserUtils.getUserName(this) + "欢迎使用");

        // 改变所有的字体的大小
        ChangeFontManager.changeFonts((ViewGroup) this.getWindow()
                .getDecorView(), this);

        mainLayout = (LinearLayout) findViewById(R.id.main_select_layout);
        freeButton = (Button) findViewById(R.id.free_button);
        mubiaoButton = (Button) findViewById(R.id.mubiao_button);
        xianshiButton = (Button) findViewById(R.id.xianshi_button);
        lookButton = (Button) findViewById(R.id.look_button);
        guideButton = (Button) findViewById(R.id.yanshitu_button);
        exitButton = (Button) findViewById(R.id.exit_button);

        freeButton.setOnClickListener(Listener);
        mubiaoButton.setOnClickListener(Listener);
        xianshiButton.setOnClickListener(Listener);
        lookButton.setOnClickListener(Listener);
        guideButton.setOnClickListener(Listener);
        exitButton.setOnClickListener(Listener);
    }

    private OnClickListener Listener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
//			mainLayout.setVisibility(View.INVISIBLE);
            mainLayout.setVisibility(View.INVISIBLE);
            switch (v.getId()) {
                case R.id.free_button:
                    intent.setClass(MenuActivity.this, FreeModel3.class);
                    break;
                case R.id.mubiao_button:
                    intent.setClass(MenuActivity.this, GoalActivity2.class);
                    break;
                case R.id.xianshi_button:
                    intent.setClass(MenuActivity.this, TimeModel2.class);
                    break;
                case R.id.look_button:
                    intent.setClass(MenuActivity.this, ShowInfoActivity.class);
                    break;
                case R.id.yanshitu_button:
                    intent.setClass(MenuActivity.this, GuideActivity.class);
                    break;
                case R.id.exit_button:
                    android.os.Process.killProcess(android.os.Process.myPid());
                    System.exit(0);
                    break;
                default:
                    break;
            }

            startActivityForResult(intent, 100); // 开始一种模式
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; th adds items to the action bar if it  present.
        getMenuInflater().inflate(R.menu.guide, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent gointIntent = new Intent(MenuActivity.this,
                        GuideActivity.class);
                startActivity(gointIntent);
                break;
            case R.id.about_autor:
                Intent gointIntent2 = new Intent(MenuActivity.this,
                        AboutMeActivity.class);
                startActivity(gointIntent2);
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mainLayout.setVisibility(View.VISIBLE);
    }

    // 再安一次推出程序
    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            // ShowToast.showNewsToast(this, content, flag, reg)
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            android.os.Process.killProcess(android.os.Process.myPid()); // /退出所有的线程
            System.exit(0);
        }
    }
}
