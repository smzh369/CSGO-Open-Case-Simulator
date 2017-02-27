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
    private String skinName;

    @Column
    private int imageId;

    @Column
    private int quality;

    @Column
    private int minWear;

    @Column
    private int maxWear;

    @Column
    private int caseId;

    @Column
    private boolean isStatTrak;

    public Weapon() {
    }

    public Weapon(String weaponName, String skinName, int imageId, int quality, int minWear, int maxWear, int caseId) {
        this.weaponName = weaponName;
        this.skinName = skinName;
        this.imageId = imageId;
        this.quality = quality;
        this.minWear = minWear;
        this.maxWear = maxWear;
        this.caseId = caseId;
    }

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

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
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

    public int getCaseId() {
        return caseId;
    }

    public void setCaseId(int caseId) {
        this.caseId = caseId;
    }

    public boolean isStatTrak() {
        return isStatTrak;
    }

    public void setStatTrak(boolean statTrak) {
        isStatTrak = statTrak;
    }
}
