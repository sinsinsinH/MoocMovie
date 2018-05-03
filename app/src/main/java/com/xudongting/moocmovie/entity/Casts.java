package com.xudongting.moocmovie.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by xudongting on 2018/4/18.
 */

public class Casts implements Parcelable{
    private String name;

    public Casts(String name) {
        this.name = name;
    }

    protected Casts(Parcel in) {
        name = in.readString();
    }

    public static final Creator<Casts> CREATOR = new Creator<Casts>() {
        @Override
        public Casts createFromParcel(Parcel in) {
            return new Casts(in);
        }

        @Override
        public Casts[] newArray(int size) {
            return new Casts[size];
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
