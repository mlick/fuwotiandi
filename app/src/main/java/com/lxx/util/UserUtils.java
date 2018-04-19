package com.lxx.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.litesuits.orm.LiteOrm;
import com.lxx.MyApplication;
import com.lxx.jlgy.bean.Person;

/**
 * Created by Administrator on 2018/4/19.
 */

public class UserUtils {
    /**
     * 获取用户的名称
     *
     * @param context
     * @return
     */
    public static String getUserName(Context context) {
        try {
            SharedPreferences sp = context.getSharedPreferences("UserInfo",
                    Context.MODE_PRIVATE);
            return sp.getString("UserName", "null");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "null";
    }

    public static boolean saveUserSportDate(int grade, int budanums, String typename) {
        LiteOrm liteOrm = MyApplication.getInstance().liteOrm;
        String userName = MyApplication.getInstance().userName;
        long r = liteOrm.insert(new Person(userName, grade, budanums, typename, TimeUtils.getCT()));
//        liteOrm.close();
        return r > 0;

    }
}
