package com.major.processdata;

import android.os.Parcel;
import android.os.Parcelable;

import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;

/**
 * @desc: TODO
 * @author: Major
 * @since: 2017/5/22 23:08
 */
@Table("ElegantBean")
public class ElegantBean implements Parcelable {

    @PrimaryKey(AssignType.AUTO_INCREMENT)
    private Long id;

    private String nick;
    private int    count;

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

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nick);
        dest.writeInt(this.count);
    }

    public ElegantBean() {}

    protected ElegantBean(Parcel in) {
        this.nick = in.readString();
        this.count = in.readInt();
    }

    public static final Parcelable.Creator<ElegantBean> CREATOR = new Parcelable.Creator<ElegantBean>() {
        @Override
        public ElegantBean createFromParcel(Parcel source) {return new ElegantBean(source);}

        @Override
        public ElegantBean[] newArray(int size) {return new ElegantBean[size];}
    };
}
