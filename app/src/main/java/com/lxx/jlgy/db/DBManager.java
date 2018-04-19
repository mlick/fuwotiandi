package com.lxx.jlgy.db;

import java.util.ArrayList;
import java.util.List;

import com.lxx.jlgy.bean.Person;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {
    private DBHelper helper;
    private SQLiteDatabase db;

    public DBManager(Context context) {
        helper = new DBHelper(context);
        //因为getWritableDatabase内部调用了mContext.openOrCreateDatabase(mName, 0, mFactory);
        //所以要确保context已初始化,我们可以把实例化DBManager的步骤放在Activity的onCreate里
        db = helper.getWritableDatabase();
    }

    /**
     * add persons
     *
     * @param persons
     */
    public void add(List<Person> persons) {
        db.beginTransaction();    //开始事务
        try {
            for (Person person : persons) {
                db.execSQL("INSERT INTO person VALUES(null, ?, ?, ?)", new Object[]{person.getName(), person.getGrade(), person.getDatetime()});
            }
            db.setTransactionSuccessful();    //设置事务成功完成
        } finally {
            db.endTransaction();    //结束事务
        }
    }

    /**
     * add one person
     *
     * @param person 添加某个用户的数据到数据中去
     */
    public void addPerson(Person person) {
        db.beginTransaction();    //开始事务
        try {
            db.execSQL("INSERT INTO person VALUES(null, ?, ?, ?)", new Object[]{person.getName(), person.getGrade(), person.getDatetime()});
            db.setTransactionSuccessful();    //设置事务成功完成
        } finally {
            db.endTransaction();    //结束事务
        }
    }

    /**
     * update person's age
     *
     * @param person
     */
    public void updateAge(Person person) {
        ContentValues cv = new ContentValues();
        cv.put("grade", person.getGrade());
        db.update("person", cv, "name = ?", new String[]{person.getName()});
    }


    /**
     * delete old person
     * @param person
     *//*
    public void deleteOldPerson(Person person) {
		db.delete("person", "grade >= ?", new String[]{String.valueOf(person.getGrade())});
	}*/

    /**
     * delete old person
     */
    public void deleteRecord(String id) {
        db.delete("person", "_id = ?", new String[]{id});
    }

    /**
     * query all persons, return lt
     *
     * @return List<Person>
     */
    public List<Person> query() {
        ArrayList<Person> persons = new ArrayList<Person>();
        Cursor c = queryTheCursor();
        while (c.moveToNext()) {
            Person person = new Person();
            person.set_id(c.getInt(c.getColumnIndex("_id")));
            person.setName(c.getString(c.getColumnIndex("name")));
            person.setGrade(c.getInt(c.getColumnIndex("grade")));
            person.setDatetime(c.getString(c.getColumnIndex("datetime")));
            persons.add(person);
        }
        c.close();
        return persons;
    }

    /**
     * query all persons, return cursor
     *
     * @return Cursor
     */
    public Cursor queryTheCursor() {
        Cursor c = db.rawQuery("SELECT * FROM person", null);
        return c;
    }

    /**
     * close database
     */
    public void closeDB() {
        db.close();
    }
}
