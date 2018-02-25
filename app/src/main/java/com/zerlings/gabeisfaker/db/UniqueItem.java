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
    private int itemType;


    public UniqueItem(){}

    public UniqueItem(Gun gun){
        this.itemName = gun.getGunName();
        this.skinName = gun.getSkinName();
        this.quality = gun.getQuality();
        this.imageId = gun.getImageId();
        this.isStatTrak = gun.isStatTrak();
        this.itemType = 0;
        Random random = new Random();
        this.wearValue = random.nextFloat()*(gun.getMaxWear()-gun.getMinWear()) + gun.getMinWear();
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

    public UniqueItem(Knife knife){
        this.itemName = knife.getKnifeName();
        this.skinName = knife.getSkinName();
        this.quality = 6;
        this.imageId = knife.getImageId();
        this.itemType = 1;
        Random random = new Random();
        this.wearValue = random.nextFloat()*(knife.getMaxWear()-knife.getMinWear()) + knife.getMinWear();
        if (knife.getKnifeName().equals(MyApplication.getContext().getString(R.string.vanilla))){
            this.exterior = null;
        }else if (wearValue >= 0 && wearValue < 7){
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
        if (random.nextInt(10) == 0){
            this.setStatTrak(true);
        }else {
            this.setStatTrak(false);
        }
    }

    public UniqueItem(Glove glove){
        this.itemName = glove.getGloveName();
        this.skinName = glove.getSkinName();
        this.quality = 6;
        this.imageId = glove.getImageId();
        this.itemType = 1;
        Random random = new Random();
        this.wearValue = random.nextFloat()*(glove.getMaxWear()-glove.getMinWear()) + glove.getMinWear();
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
        this.isStatTrak = false;
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

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public boolean isStatTrak() {
        return isStatTrak;
    }

    public void setStatTrak(boolean isStatTrak) {
        this.isStatTrak = isStatTrak;
    }
}
