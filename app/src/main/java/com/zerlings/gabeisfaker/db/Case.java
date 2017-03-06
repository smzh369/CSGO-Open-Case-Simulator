package com.zerlings.gabeisfaker.db;

import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by 令子 on 2017/2/13.
 */

public class Case extends BaseModel {

    private int id;

    private String caseName;

    private int imageId;

    public Case(){}

    public Case(String caseName, int imageId) {
        this.caseName = caseName;
        this.imageId = imageId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}