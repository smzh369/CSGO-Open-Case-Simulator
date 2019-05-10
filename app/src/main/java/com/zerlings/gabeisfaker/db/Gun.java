package com.zerlings.gabeisfaker.db;


/**
 * Created by smzh369 on 2017/2/13.
 */

public class Gun {

    private int id;

    private String gunName;

    private String skinName;

    private String imageName;

    private int quality;

    private int minWear;

    private int maxWear;

    private String caseName;

    private boolean isStatTrak;

    public Gun() {
    }

    public Gun(String gunName, String skinName, String imageName, int quality, int minWear, int maxWear, String caseName) {
        this.gunName = gunName;
        this.skinName = skinName;
        this.imageName = imageName;
        this.quality = quality;
        this.minWear = minWear;
        this.maxWear = maxWear;
        this.caseName = caseName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGunName() {
        return gunName;
    }

    public void setGunName(String gunName) {
        this.gunName = gunName;
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

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    public boolean isStatTrak() {
        return isStatTrak;
    }

    public void setStatTrak(boolean statTrak) {
        isStatTrak = statTrak;
    }
}
