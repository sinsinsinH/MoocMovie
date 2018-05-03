package com.xudongting.moocmovie.entity;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by xudongting on 2018/4/18.
 */

public class Movice implements Parcelable {
    private String id;
    private Rating rating;
    private List<String> types;
    private String title;
    private String description;
    private List<Casts> casts;
    private List<Directors> directors;
    private int year;
    private String imageUrl;

    public Movice() {
    }

    public Movice(String id, Rating rating, List<String> types, String title, String description, List<Casts> casts, List<Directors> directors, int year, String imageUrl) {
        this.id = id;
        this.rating = rating;
        this.types = types;
        this.title = title;
        this.description = description;
        this.casts = casts;
        this.directors = directors;
        this.year = year;
        this.imageUrl = imageUrl;
    }

    protected Movice(Parcel in) {
        id = in.readString();
        rating=in.readParcelable(Rating.class.getClassLoader());
        types = in.createStringArrayList();
        title = in.readString();
        description = in.readString();
        casts=in.createTypedArrayList(Casts.CREATOR);
        directors=in.createTypedArrayList(Directors.CREATOR);
        year = in.readInt();
        imageUrl = in.readString();
    }

    public static final Creator<Movice> CREATOR = new Creator<Movice>() {
        @Override
        public Movice createFromParcel(Parcel in) {
            return new Movice(in);
        }

        @Override
        public Movice[] newArray(int size) {
            return new Movice[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Casts> getCasts() {
        return casts;
    }

    public void setCasts(List<Casts> casts) {
        this.casts = casts;
    }

    public List<Directors> getDirectors() {
        return directors;
    }

    public void setDirectors(List<Directors> directors) {
        this.directors = directors;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Movice{" +
                "id='" + id + '\'' +
                ", rating=" + rating +
                ", types=" + types +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", casts=" + casts +
                ", derectors=" + directors +
                ", year=" + year +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeParcelable(rating,flags);
        dest.writeStringList(types);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeTypedList(casts);
        dest.writeTypedList(directors);
        dest.writeInt(year);
        dest.writeString(imageUrl);
    }
}


