package com.rajeshk.healthdiet.Model;

/**
 * Created by ChRajeshKumar on 13-Apr-17.
 */

public class Queary {
    private String key_value;
    private int limit;

    public Queary(String key_value,int limit){
        this.key_value = key_value;
        this.limit = limit;
    }
    public String getKey_value() {
        return key_value;
    }

    public void setKey_value(String key_value) {
        this.key_value = key_value;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
