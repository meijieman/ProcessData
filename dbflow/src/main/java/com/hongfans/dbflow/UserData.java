package com.hongfans.dbflow;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * 需要继承 BaseModel，该类实现了 CRUD
 * Created by MEI on 2018/2/25.
 */
@Table(database = AppDatabase.class)
public class UserData extends BaseModel{

    @PrimaryKey(autoincrement = true)
    public  long id;

    @Column
    public String name;

    @Column
    public int age;

    @Column
    public boolean sex;


    @Override
    public String toString() {
        return "UserData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                '}';
    }
}
