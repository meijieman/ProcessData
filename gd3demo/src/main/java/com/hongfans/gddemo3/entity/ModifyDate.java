package com.hongfans.gddemo3.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 日记修改记录
 * Created by MEI on 2017/12/26.
 */
@Entity
public class ModifyDate {

    @Id(autoincrement = true)
    private Long id;

    private String date;

    private long fk_noteId;


    @Generated(hash = 2075409572)
    public ModifyDate(Long id, String date, long fk_noteId) {
        this.id = id;
        this.date = date;
        this.fk_noteId = fk_noteId;
    }

    @Generated(hash = 1884475852)
    public ModifyDate() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "ModifyDate{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", fk_noteId=" + fk_noteId +
                '}';
    }

    public long getFk_noteId() {
        return this.fk_noteId;
    }

    public void setFk_noteId(long fk_noteId) {
        this.fk_noteId = fk_noteId;
    }
}
