package com.lxx.jlgy.bean;

import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;

@Table("sport_data")
public class Person {

    // 指定自增，每个对象需要有一个主键
    @PrimaryKey(AssignType.AUTO_INCREMENT)
    private int _id;

    private String name;// 用户名称
    private int grade;// 总成绩
    private String datetime;
    private int budanums;// 补打次数
    private String typename;//类型名称 自由模式，限时模式，目标模式


    public Person() {
    }

    public Person(String userName, int grade, int budanums, String typename, String ct) {
        this.name = userName;
        this.grade = grade;
        this.budanums = budanums;
        this.typename = typename;
        this.datetime = ct;
    }

    public Person(String user_id) {
        this._id = Integer.parseInt(user_id);
    }


    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public int getBudanums() {
        return budanums;
    }

    public void setBudanums(int budanums) {
        this.budanums = budanums;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
}
