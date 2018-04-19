package com.lxx.jlgy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.assit.WhereBuilder;
import com.lxx.MyApplication;
import com.lxx.jlgy.bean.Person;
import com.lxx.jlgy.db.DBManager;
import com.lxx.util.ChangeFontManager;
import com.lxx.util.ShowToast;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class ShowInfoActivity extends Activity {

    //	private DBManager mgr;
    private LiteOrm liteOrm;

    private ListView listView;
    private ArrayList<Map<String, String>> lt;
    //private MyAdapter adapter;
    private SimpleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_info);
        ChangeFontManager.changeFonts((ViewGroup) this.getWindow().getDecorView(), this);
        listView = (ListView) findViewById(R.id.listView);
        // 初始化DBManager
//		mgr = new DBManager(this);
        liteOrm = MyApplication.getInstance().liteOrm;

        // add(getCurrentFocus());
        query(getCurrentFocus());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 应用的最后一个Activity关闭时应释放DB
//        mgr.closeDB();
//        liteOrm.close();
    }

    public void query(View view) {
//        List<Person> persons = mgr.query();
        List<Person> persons = liteOrm.query(Person.class);
        lt = new ArrayList<Map<String, String>>();

        System.out.println("从数据库中的个数为 ： " + persons.size());

        for (Person person : persons) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("user_id", String.valueOf(person.get_id()));
            map.put("name", person.getName());
            map.put("info", String.valueOf(person.getGrade()));
            map.put("datetime", person.getDatetime());
            lt.add(map);
        }

        System.out.println("lt的个数为 ： " + lt.size());

        adapter = new SimpleAdapter(this, lt, R.layout.show_user_info,
                new String[]{"name", "info", "datetime"}, new int[]{
                R.id.user_name, R.id.user_grade, R.id.date_time});

        //adapter = new MyAdapter(getApplicationContext(),lt);

        listView.setAdapter(adapter);
        listView.setOnItemLongClickListener(new OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int arg2, long arg3) {
                ShowDeleteDialog(arg2);
                /*
                 * lt.remove(arg2-1);
				 * mgr.deleteRecord(lt.get(arg2).get("user_id"));
				 * ShowToast.showNewsToast(getApplicationContext(), "您点击的Id为 ： "
				 * + lt.get(arg2).get("user_id") + "时间为 ： " +
				 * lt.get(arg2).get("datetime") + "删除成功！");
				 * adapter.notifyDataSetChanged();
				 */
                return false;
            }
        });
    }

    protected void ShowDeleteDialog(final int currentId) {

        // final int currentId = id;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("是否删除");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
//                System.out.println(currentId);
//                mgr.deleteRecord(lt.get(currentId).get("user_id")); // 先将数据库中的数据删掉
//                liteOrm.delete(new Person());
                int id = Integer.parseInt(lt.get(currentId).get("user_id"));

                liteOrm.delete(new WhereBuilder(Person.class).where("_id=", id));
                lt.remove(currentId); // 再将lt中的数据删除掉，否者会产生错误
                ShowToast.showNewsToast(getApplicationContext(), "删除成功！");
                adapter.notifyDataSetChanged();
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
//				arg0.dismiss();
                arg0.dismiss();
            }
        });
        builder.show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            ShowInfoActivity.this.setResult(RESULT_OK);
            ShowInfoActivity.this.finish();
        }

        return super.onKeyDown(keyCode, event);
    }

}
