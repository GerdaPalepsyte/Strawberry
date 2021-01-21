package com.example.strawberry;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class ListItem implements Parcelable{

    private String name;
    private byte[] image;
    private String description;

    public ListItem() {}

    public ListItem(String name, byte[] image,  String description){
        this.name = name;
        this.image = image;
        this.description = description;
    }

    protected ListItem(Parcel in) {
        name = in.readString();
        image = (byte[]) in.readValue(byte[].class.getClassLoader());
        description = in.readString();
    }

    public static final Parcelable.Creator<ListItem> CREATOR = new Parcelable.Creator<ListItem>() {
        @Override
        public ListItem createFromParcel(Parcel parcel) {
            return new ListItem(parcel);
        }

        @Override
        public ListItem[] newArray(int i) {
            return new ListItem[i];
        }
    };

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public byte[] getImage(){
        return image;
    }

    public void setImage(byte[] image){
        this.image = image;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeValue(image);
        parcel.writeString(description);

    }
}
