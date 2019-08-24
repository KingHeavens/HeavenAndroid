package com.heaven.android.service;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 功能描述
 *
 * @author Aaron
 * @version 1.0
 * @since create time: 2019-08-24
 ***/
public class Book implements Parcelable {
    private String name;
    public Book(Parcel in) {
        this.name = in.readString();
    }

    public Book(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }

    void readFromParcel(Parcel parcel) {
        parcel.writeString(name);
    }
}
