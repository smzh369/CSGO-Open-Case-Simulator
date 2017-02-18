package com.zerlings.gabeisfaker.db;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by 令子 on 2017/2/13.
 */

@Table(database = AppDatabase.class)
public class UniqueWeapon extends BaseModel {

    @PrimaryKey(autoincrement = true)
    private int id;

    @Column
    private String weaponName;

    @Column
    private String skinName;

    @Column
    private int ImageId;

    @Column
    private int quality;

    @Column
    private String exterior;

    @Column float wearValue;

    @Column
    private boolean isStatTrak = false;

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
        return ImageId;
    }

    public void setImageId(int ImageId) {
        this.ImageId = ImageId;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public String getExterior() {
        return exterior;
    }

    public void setExterior(String exterior) {
        this.exterior = exterior;
    }

    public float getWearValue() {
        return wearValue;
    }

    public void setWearValue(float wearValue) {
        this.wearValue = wearValue;
    }

    public boolean isStatTrak() {
        return isStatTrak;
    }

    public void setStatTrak(boolean statTrak) {
        isStatTrak = statTrak;
    }
}
