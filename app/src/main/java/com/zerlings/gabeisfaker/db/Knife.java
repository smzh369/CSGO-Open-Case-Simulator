package com.zerlings.gabeisfaker.db;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by 令子 on 2018/2/18.
 */

@Table(database = AppDatabase.class)
public class Knife extends BaseModel {

    @PrimaryKey(autoincrement = true)
    private int id;

    @Column
    private String knifeName;

    @Column
    private String skinName;

    @Column
    private int imageId;

    @Column
    private float minWear;

    @Column
    private float maxWear;


    public Knife() {
    }

    public Knife(String knifeName, String skinName, int imageId, int minWear, int maxWear) {

        this.knifeName = knifeName;
        this.skinName = skinName;
        this.imageId = imageId;
        this.minWear = minWear;
        this.maxWear = maxWear;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
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
