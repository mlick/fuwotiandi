package com.lxx.util;

import com.lxx.jlgy.bean.Person;
import com.lxx.jlgy.db.DBManager;

import android.content.Context;
import android.content.SharedPreferences;

public class addPersonToDB {

	private Context context;

	// 构造函数的数据库
	public addPersonToDB(Context context) {
		this.context = context;
	}

	/**
	 *@author 李祥鑫 lxx
	 *@CreateDate 2014-4-8 下午7:19:16
	 *@param grade 将成绩添加到用户的数据库中
	 * 添加用户的数据
	 */
	public void APTB(int grade) {
		DBManager dbmgr = new DBManager(context);
		Person person = new Person();
		person.setName(getPersonName());
		person.setGrade(grade);
		person.setDatetime(TimeUtils.getCT());
		dbmgr.addPerson(person);
	}

	/**
	 *@author 李祥鑫 lxx
	 *@CreateDate 2014-4-8 下午7:18:15
	 *@return 返回用户的姓名
	 * 得到用户的名字
	 */
	public String getPersonName() {

		if (context != null) {
			SharedPreferences sp = context.getSharedPreferences("UserInfo",Context.MODE_PRIVATE);
			return sp.getString("UserName", "null");
		}
		return "";
	}

}
