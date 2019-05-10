package com.zerlings.gabeisfaker.db;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by smzh369 on 2018/2/18.
 */

@Table(database = AppDatabase.class)
public class Knife extends BaseModel {

    @Column
    private String knifeName;

    @Column
    private String skinName;

    @PrimaryKey
    private String imageName;

    @Column
    private float minWear;

    @Column
    private float maxWear;


    public Knife() {
    }

    public Knife(String knifeName, String skinName, String imageName, int minWear, int maxWear) {

        this.knifeName = knifeName;
        this.skinName = skinName;
        this.imageName = imageName;
        this.minWear = minWear;
        this.maxWear = maxWear;

    }

    public String getKnifeName() {
        return knifeName;
    }

    public void setKnifeName(String knifeName) {
        this.knifeName = knifeName;
    }

    public String getSkinName() {
        return skinName;
    }

    public void setSkinName(String skinName) {
        this.skinName = skinName;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public float getMinWear() {
        return minWear;
    }

    public void setMinWear(float minWear) {
        this.minWear = minWear;
    }

    public float getMaxWear() {
        return maxWear;
    }

    public void setMaxWear(float maxWear) {
        this.maxWear = maxWear;
    }

}
