package com.zerlings.gabeisfaker.gson;

/**
 * Created by smzh369 on 2018/1/8.
 */

public class Sale {

    /**
     * success : true
     * lowest_price : $59.13
     * volume : 5
     * median_price : $42.64
     */

    private boolean success;
    private String lowest_price;
    private String volume;
    private String median_price;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getLowest_price() {
        return lowest_price;
    }

    public void setLowest_price(String lowest_price) {
        this.lowest_price = lowest_price;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getMedian_price() {
        return median_price;
    }

    public void setMedian_price(String median_price) {
        this.median_price = median_price;
    }

}
