package com.zerlings.gabeisfaker.db;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by 令子 on 2017/2/13.
 */

@Table(database = AppDatabase.class)
public class Weapon extends BaseModel {

    @PrimaryKey(autoincrement = true)
    private int id;

    @Column
    private String weaponName;

    @Column
    private String quality;

    @Column
    private int minWear;

    @Column
    private int maxWear;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWeaponName() {
        return weaponName;
    }

    public void setWeaponName(String weaponName) {
        this.weaponName = weaponName;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public int getMinWear() {
        return minWear;
    }

    public void setMinWear(int minWear) {
        this.minWear = minWear;
    }

    public int getMaxWear() {
        return maxWear;
    }

    public void setMaxWear(int maxWear) {
        this.maxWear = maxWear;
    }
}
