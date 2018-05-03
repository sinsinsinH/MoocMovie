package com.xudongting.moocmovie.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by xudongting on 2018/4/18.
 */

public class Rating implements Parcelable{
    private int max;
    private float average;
    private String stars;
    private int min;

    public Rating(int max, int average, String stars, int min) {
        this.max = max;
        this.average = average;
        this.stars = stars;
        this.min = min;
    }

    protected Rating(Parcel in) {
        max = in.readInt();
        average = in.readFloat();
        stars = in.readString();
        min = in.readInt();
    }

    public static final Creator<Rating> CREATOR = new Creator<Rating>() {
        @Override
        public Rating createFromParcel(Parcel in) {
            return new Rating(in);
        }

        @Override
        public Rating[] newArray(int size) {
            return new Rating[size];
        }
    };

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public float getAverage() {
        return average;
    }

    public void setAverage(int average) {
        this.average = average;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "max=" + max +
                ", average=" + average +
                ", stars='" + stars + '\'' +
                ", min=" + min +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(max);
        dest.writeFloat(average);
        dest.writeString(stars);
        dest.writeInt(min);
    }
}
