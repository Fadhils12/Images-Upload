package com.example.imageupload.model;

public class Upload {

    private String mName;
    private String mImageUrl;
    private String mDesc;
    private int position;
    private String mKey;

    public String getmKey() {
        return mKey;
    }

    public void setmKey(String mKey) {
        this.mKey = mKey;
    }

    public Upload() {
        //empty constructor needed
    }

    public Upload (int position){
        this.position = position;
    }

    public Upload(String name, String desc, String imageUrl) {
        if (name.trim().equals("")){
            name = "Nothing Name";
        } else if (desc.trim().equals("")){
            desc = "Nothing Description";
        }
        this.mName = name;
        this.mDesc = desc;
        this.mImageUrl = imageUrl;

    }

    public String getName() {
        return mName;
    }

    public void setDesc(String name) {
        this.mDesc = name;
    }

    public String getDesc() {
        return mDesc;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.mImageUrl = imageUrl;
    }



}
