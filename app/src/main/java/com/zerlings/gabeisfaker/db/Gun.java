package com.zerlings.gabeisfaker.db;


/**
 * Created by 令子 on 2017/2/13.
 */

public class Gun {

    private int id;

    private String gunName;

    private String skinName;

    private int imageId;

    private int quality;

    private int minWear;

    private int maxWear;

    private int caseId;

    private boolean isStatTrak;

    public Gun() {
    }

    public Gun(String gunName, String skinName, int imageId, int quality, int minWear, int maxWear, int caseId) {
        this.gunName = gunName;
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
