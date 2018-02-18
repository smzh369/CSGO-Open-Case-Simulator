package com.zerlings.gabeisfaker.db;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.zerlings.gabeisfaker.MyApplication;
import com.zerlings.gabeisfaker.R;

import java.util.Random;

/**
 * Created by 令子 on 2018/2/17.
 */

@Table(database = AppDatabase.class)
public class UniqueItem extends BaseModel {

    @PrimaryKey(autoincrement = true)
    private int id;

    @Column
    private String itemName;

    @Column
    private String skinName;

    @Column
    private int imageId;

    @Column
    private int quality;

    @Column
    private String exterior;

    @Column
    private float wearValue;

    @Column
    private boolean isStatTrak;

    @Column
    private int type;


    public UniqueItem(){}

    public UniqueItem(Weapon weapon){
        this.itemName = weapon.getWeaponName();
        this.skinName = weapon.getSkinName();
        this.quality = weapon.getQuality();
        this.imageId = weapon.getImageId();
        this.isStatTrak = weapon.isStatTrak();
        this.type = 0;
        Random random = new Random();
        this.wearValue = random.nextFloat()*(weapon.getMaxWear()-weapon.getMinWear()) + weapon.getMinWear();
        if (wearValue >= 0 && wearValue < 7){
            this.exterior = MyApplication.getContext().getResources().getString(R.string.factory_new);
        }else if (wearValue >= 7 && wearValue < 15){
            this.exterior = MyApplication.getContext().getResources().getString(R.string.minimal_wear);
        }else if (wearValue >= 15 && wearValue < 38){
            this.exterior = MyApplication.getContext().getResources().getString(R.string.field_tested);
        }else if (wearValue >= 38 && wearValue < 45){
            this.exterior = MyApplication.getContext().getResources().getString(R.string.well_worn);
        }else {
            this.exterior = MyApplication.getContext().getResources().getString(R.string.battle_scarred);
        }
    }

    public UniqueItem(RareItem rareItem){
        this.itemName = rareItem.getItemName();
        this.skinName = rareItem.getSkinName();
        this.quality = 6;
        this.imageId = rareItem.getImageId();
        this.type = rareItem.getType();
        Random random = new Random();
        this.wearValue = random.nextFloat()*(rareItem.getMaxWear()-rareItem.getMinWear()) + rareItem.getMinWear();
        if (wearValue >= 0 && wearValue < 7){
            this.exterior = MyApplication.getContext().getString(R.string.factory_new);
        }else if (wearValue >= 7 && wearValue < 15){
            this.exterior = MyApplication.getContext().getString(R.string.minimal_wear);
        }else if (wearValue >= 15 && wearValue < 38){
            this.exterior = MyApplication.getContext().getString(R.string.field_tested);
        }else if (wearValue >= 38 && wearValue < 45){
            this.exterior = MyApplication.getContext().getString(R.string.well_worn);
        }else {
            this.exterior = MyApplication.getContext().getString(R.string.battle_scarred);
        }
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

    public void setImageId(int ImageId) {
        this.imageId = ImageId;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isStatTrak() {
        return isStatTrak;
    }

    public void setStatTrak(boolean isStatTrak) {
        this.isStatTrak = isStatTrak;
    }
}
