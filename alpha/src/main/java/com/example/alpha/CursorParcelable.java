package com.example.alpha;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * TODO
 * Created by MEI on 2018/2/27.
 */

public class CursorParcelable implements Parcelable {


    protected CursorParcelable(Parcel in) {
    }

    public static final Creator<CursorParcelable> CREATOR = new Creator<CursorParcelable>() {
        @Override
        public CursorParcelable createFromParcel(Parcel in) {
            return new CursorParcelable(in);
        }

        @Override
        public CursorParcelable[] newArray(int size) {
            return new CursorParcelable[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {}
}
