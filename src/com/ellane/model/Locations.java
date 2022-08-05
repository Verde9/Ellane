package com.ellane.model;

public class Locations {

        private String name;
        private String description;
        private String north;
        private String west;
        private String east;
        private String south;
        private String item;
        private String itemDescription;

    public Locations () {
    }

    public Locations(String item, String description, String west, String south, String north, String itemDescription, String east, String name) {
        this.item = item;
        this.description = description;
        this.west = west;
        this.south = south;
        this.north = north;
        this.itemDescription = itemDescription;
        this.east = east;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getNorth() {
        return north;
    }

    public String getWest() {
        return west;
    }

    public String getEast() {
        return east;
    }

    public String getSouth() {
        return south;
    }

    public String getItem() {
        return item;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNorth(String north) {
        this.north = north;
    }

    public void setWest(String west) {
        this.west = west;
    }

    public void setEast(String east) {
        this.east = east;
    }

    public void setSouth(String south) {
        this.south = south;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    @Override
    public String toString() {
        return "Locations{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", north='" + north + '\'' +
                ", west='" + west + '\'' +
                ", east='" + east + '\'' +
                ", south='" + south + '\'' +
                ", item='" + item + '\'' +
                ", itemDescription='" + itemDescription + '\'' +
                '}';
    }
}
