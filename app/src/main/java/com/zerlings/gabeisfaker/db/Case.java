package com.zerlings.gabeisfaker.db;


/**
 * Created by 令子 on 2017/2/13.
 */

public class Case {

    private int id;

    private String caseName;

    private int imageId;

    private String[] rareItemType;

    private String[] rareSkinType;

    public Case(){}

    public Case(String caseName, int imageId, String[] rareItemType, String[] rareSkinType) {
        this.caseName = caseName;
        this.imageId = imageId;
        this.rareItemType = rareItemType;
        this.rareSkinType = rareSkinType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String[] getRareItemType() {
        return rareItemType;
    }

    public void setRareItemType(String[] rareItemType) {
        this.rareItemType = rareItemType;
    }

    public String[] getRareSkinType() {
        return rareSkinType;
    }

    public void setRareSkinType(String[] rareSkinType) {
        this.rareSkinType = rareSkinType;
    }
}
