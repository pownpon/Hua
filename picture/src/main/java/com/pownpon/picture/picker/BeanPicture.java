package com.pownpon.picture.picker;

/**
 * Copyright (C), 2021-2030, XXX有限公司
 * FileName: BeanPicture
 * Author: HUA
 * Date: 2021/6/22 17:13
 * Description:
 * History:
 */
class BeanPicture {

    BeanPicture(){}

    BeanPicture(String path){
        this.mPicturePath = path;
    }

    private String mPicturePath;

    private boolean mSelected;

    public String getPicturePath() {
        return mPicturePath;
    }

    public void setPicturePath(String micturePath) {
        this.mPicturePath = micturePath;
    }

    public boolean isSelected() {
        return mSelected;
    }

    public void setSelected(boolean selected) {
        this.mSelected = selected;
    }
}
