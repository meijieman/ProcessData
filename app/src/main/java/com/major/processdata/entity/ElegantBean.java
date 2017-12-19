package com.major.processdata.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @desc: TODO
 * @author: Major
 * @since: 2017/5/22 23:08
 */
@Table("ElegantBean")
@Entity(nameInDb = "ElegantBean")
public class ElegantBean implements Parcelable {

    @Id(autoincrement = true)
    @PrimaryKey(AssignType.AUTO_INCREMENT)
    private Long id;

    private String nick;
    private int count;

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "ElegantBean{" +
                "id=" + id +
                ", nick='" + nick + '\'' +
                ", count=" + count +
                '}';
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.nick);
        dest.writeInt(this.count);
    }

    public ElegantBean() {}

    protected ElegantBean(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.nick = in.readString();
        this.count = in.readInt();
    }

    @Generated(hash = 1511492223)
    public ElegantBean(Long id, String nick, int count) {
        this.id = id;
        this.nick = nick;
        this.count = count;
    }

    public static final Parcelable.Creator<ElegantBean> CREATOR = new Parcelable.Creator<ElegantBean>() {
        @Override
        public ElegantBean createFromParcel(Parcel source) {return new ElegantBean(source);}

        @Override
        public ElegantBean[] newArray(int size) {return new ElegantBean[size];}
    };
}
