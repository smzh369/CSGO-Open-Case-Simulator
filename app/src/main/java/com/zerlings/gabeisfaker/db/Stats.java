package com.zerlings.gabeisfaker.db;

public class Stats {

    private String rareCount = "0";
    private String convertCount = "0";
    private String classifiedCount = "0";
    private String restrictedCount = "0";
    private String milspecCount = "0";
    private String totalCount = "0";

    public String getRareCount() {
        return rareCount;
    }

    public void setRareCount(String rareCount) {
        this.rareCount = rareCount;
    }

    public String getConvertCount() {
        return convertCount;
    }

    public void setConvertCount(String convertCount) {
        this.convertCount = convertCount;
    }

    public String getClassifiedCount() {
        return classifiedCount;
    }

    public void setClassifiedCount(String classifiedCount) {
        this.classifiedCount = classifiedCount;
    }

    public String getRestrictedCount() {
        return restrictedCount;
    }

    public void setRestrictedCount(String restrictedCount) {
        this.restrictedCount = restrictedCount;
    }

    public String getMilspecCount() {
        return milspecCount;
    }

    public void setMilspecCount(String milspecCount) {
        this.milspecCount = milspecCount;
    }

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }
}
