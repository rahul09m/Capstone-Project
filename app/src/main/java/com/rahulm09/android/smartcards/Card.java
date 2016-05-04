package com.rahulm09.android.smartcards;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by rmenezes on 5/2/2016.
 */
public class Card implements Parcelable {
    Integer id;
    String name;
    String number;
    String format;


    public Card (Integer mId, String mName, String mNumber, String mFormat){
        this.id = mId;
        this.name = mName;
        this.number = mNumber;
        this.format = mFormat;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.name);
        dest.writeString(this.number);
        dest.writeString(this.format);
    }

    protected Card(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.name = in.readString();
        this.number = in.readString();
        this.format = in.readString();
    }

    public static final Parcelable.Creator<Card> CREATOR = new Parcelable.Creator<Card>() {
        @Override
        public Card createFromParcel(Parcel source) {
            return new Card(source);
        }

        @Override
        public Card[] newArray(int size) {
            return new Card[size];
        }
    };
}
