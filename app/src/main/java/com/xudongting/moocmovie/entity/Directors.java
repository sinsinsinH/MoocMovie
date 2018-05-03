package com.xudongting.moocmovie.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by xudongting on 2018/4/18.
 */

public class Directors implements Parcelable{
    private String name;

    public Directors(String name) {
        this.name = name;
    }

    protected Directors(Parcel in) {
        name = in.readString();
    }

    public static final Creator<Directors> CREATOR = new Creator<Directors>() {
        @Override
        public Directors createFromParcel(Parcel in) {
            return new Directors(in);
        }

        @Override
        public Directors[] newArray(int size) {
            return new Directors[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }
}
