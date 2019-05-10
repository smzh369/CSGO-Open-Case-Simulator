package com.zerlings.gabeisfaker.db;


/**
 * Created by smzh369 on 2017/2/13.
 */

public class Case {

    private String caseName;

    private String imageName;

    private String[] rareItemType;

    private String[] rareSkinType;

    public Case(){}

    public Case(String caseName, String imageName, String[] rareItemType, String[] rareSkinType) {
        this.caseName = caseName;
        this.imageName = imageName;
        this.rareItemType = rareItemType;
        this.rareSkinType = rareSkinType;
    }

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
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
