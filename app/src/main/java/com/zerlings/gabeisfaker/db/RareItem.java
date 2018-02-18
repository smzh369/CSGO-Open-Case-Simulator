package com.zerlings.gabeisfaker.db;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by 令子 on 2018/2/18.
 */

@Table(database = AppDatabase.class)
public class RareItem extends BaseModel {

    @PrimaryKey(autoincrement = true)
    private int id;

    @Column
    private String itemName;

    @Column
    private String skinName;

    @Column
    private int imageId;

    @Column
    private float minWear;

    @Column
    private float maxWear;

    @Column
    private int type;


    public RareItem() {
    }

    public RareItem(String knifeName, String skinName, int imageId, int minWear, int maxWear,int type) {

        this.itemName = knifeName;
        this.skinName = skinName;
        this.imageId = imageId;
        this.minWear = minWear;
        this.maxWear = maxWear;
        this.type = type;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
