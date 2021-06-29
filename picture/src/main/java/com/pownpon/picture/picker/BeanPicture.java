package com.pownpon.picture.picker;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Copyright (C), 2021-2030, XXX有限公司
 * FileName: BeanPicture
 * Author: HUA
 * Date: 2021/6/22 17:13
 * Description:
 * History:
 */
public class BeanPicture implements Parcelable {


    private int pictureId;
    private String picturePath;
    private Uri pictureUri;
    private int picWidth;
    private int picHeight;
    private int pictureOrientation;
    private boolean selected;
    private Bitmap.Config pictureConfig;

    public BeanPicture() {
    }

    public BeanPicture(int pictureId, String picturePath, Uri pictureUri, int picWidth, int picHeight, int pictureOrientation) {
        this.pictureId = pictureId;
        this.picturePath = picturePath;
        this.pictureUri = pictureUri;
        this.picWidth = picWidth;
        this.picHeight = picHeight;
        this.pictureOrientation = pictureOrientation;
    }

    public BeanPicture(int pictureId, String picturePath, Uri pictureUri, int picWidth, int picHeight, int pictureOrientation, boolean selected, Bitmap.Config pictureConfig) {
        this.pictureId = pictureId;
        this.picturePath = picturePath;
        this.pictureUri = pictureUri;
        this.picWidth = picWidth;
        this.picHeight = picHeight;
        this.pictureOrientation = pictureOrientation;
        this.selected = selected;
        this.pictureConfig = pictureConfig;
    }

    protected BeanPicture(Parcel in) {
        pictureId = in.readInt();
        picturePath = in.readString();
        pictureUri = in.readParcelable(Uri.class.getClassLoader());
        picWidth = in.readInt();
        picHeight = in.readInt();
        pictureOrientation = in.readInt();
        selected = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(pictureId);
        dest.writeString(picturePath);
        dest.writeParcelable(pictureUri, flags);
        dest.writeInt(picWidth);
        dest.writeInt(picHeight);
        dest.writeInt(pictureOrientation);
        dest.writeByte((byte) (selected ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<BeanPicture> CREATOR = new Creator<BeanPicture>() {
        @Override
        public BeanPicture createFromParcel(Parcel in) {
            return new BeanPicture(in);
        }

        @Override
        public BeanPicture[] newArray(int size) {
            return new BeanPicture[size];
        }
    };

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public int getPictureId() {
        return pictureId;
    }

    public void setPictureId(int pictureId) {
        this.pictureId = pictureId;
    }

    public int getPictureOrientation() {
        return pictureOrientation;
    }

    public void setPictureOrientation(int pictureOrientation) {
        this.pictureOrientation = pictureOrientation;
    }

    public Uri getPictureUri() {
        return pictureUri;
    }

    public void setPictureUri(Uri pictureUri) {
        this.pictureUri = pictureUri;
    }

    public Bitmap.Config getPictureConfig() {
        return pictureConfig;
    }

    public void setPictureConfig(Bitmap.Config pictureConfig) {
        this.pictureConfig = pictureConfig;
    }


    public int getPicWidth() {
        return picWidth;
    }

    public void setPicWidth(int picWidth) {
        this.picWidth = picWidth;
    }

    public int getPicHeight() {
        return picHeight;
    }

    public void setPicHeight(int picHeight) {
        this.picHeight = picHeight;
    }

    @Override
    public String toString() {
        return "BeanPicture{" +
                "pictureId=" + pictureId +
                ", picturePath='" + picturePath + '\'' +
                ", pictureUri=" + pictureUri +
                ", picWidth=" + picWidth +
                ", picHeight=" + picHeight +
                ", pictureOrientation=" + pictureOrientation +
                ", selected=" + selected +
                ", pictureConfig=" + pictureConfig +
                '}';
    }
}
