package com.hongfans.gddemo3.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Transient;

/**
 * TODO
 * Created by MEI on 2017/12/19.
 */

@Entity
public class DailyNote extends Note {

    private String date;

    @Transient
    private boolean isSave;

    public boolean isSave() {
        return isSave;
    }

    public void setSave(boolean save) {
        isSave = save;
    }

    @Generated(hash = 1969121170)
    public DailyNote() {
    }

    @Generated(hash = 820359669)
    public DailyNote(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "DailyNote{" +
                super.toString() +
                "date='" + date + '\'' +
                ", isSave=" + isSave +
                '}';
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
