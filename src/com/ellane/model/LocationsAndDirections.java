package com.ellane.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class LocationsAndDirections {

    private String name;
    private String north;
    private String south;
    private String east;
    private String west;
    private String desc;
    private String item;
    private String item_status;
    private String item2;
    private String item_status2;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNorth() {
        return north;
    }

    public void setNorth(String north) {
        this.north = north;
    }

    public String getSouth() {
        return south;
    }

    public void setSouth(String south) {
        this.south = south;
    }

    public String getEast() {
        return east;
    }

    public void setEast(String east) {
        this.east = east;
    }

    public String getWest() {
        return west;
    }

    public void setWest(String west) {
        this.west = west;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getItem_status() {
        return item_status;
    }

    public void setItem_status(String item_status) {
        this.item_status = item_status;
    }

    public String getItem2() {
        return item2;
    }

    public void setItem2(String item2) {
        this.item2 = item2;
    }

    public String getItem_status2() {
        return item_status2;
    }

    public void setItem_status2(String item_status2) {
        this.item_status2 = item_status2;
    }

    @Override
    public String toString() {
        return "LocationsAndDirections{" +
                "name='" + name + '\'' +
                ", north='" + north + '\'' +
                ", south='" + south + '\'' +
                ", east='" + east + '\'' +
                ", west='" + west + '\'' +
                ", desc='" + desc + '\'' +
                ", item='" + item + '\'' +
                ", item_status='" + item_status + '\'' +
                ", item2='" + item2 + '\'' +
                ", item_status2='" + item_status2 + '\'' +
                '}';
    }
}