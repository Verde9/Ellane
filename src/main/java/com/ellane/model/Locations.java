package com.ellane.model;


import com.ellane.character.Terrorist;

public class Locations {

    private String name;
    private String description;
    private String north;
    private String northDescription;
    private String west;
    private String westDescription;
    private String east;
    private String eastDescription;
    private String south;
    private String southDescription;
    private Items item;
    private int itemPlacementSouth;
    private int itemPlacementEast;
    private int itemPlacementWest;
    private int itemPlacementNorth;
    private Terrorist terrorist;
    private int terroristPlacement;

    public Locations () {
    }

    public Locations(Items item, String description, String west, String westDescription ,String south, String southDescription ,String north,
                     String northDescription, String east, String eastDescription, String name,
                     int itemPlacementEast, int itemPlacementNorth, int itemPlacementWest,
                     int itemPlacementSouth, Terrorist terrorist, int terroristPlacement)
    {
        this.item = item;
        this.description = description;
        this.west = west;
        this.westDescription = westDescription;
        this.south = south;
        this.southDescription = southDescription;
        this.north = north;
        this.northDescription = northDescription;
        this.east = east;
        this.eastDescription = eastDescription;
        this.name = name;
        this.itemPlacementEast = itemPlacementEast;
        this.itemPlacementNorth = itemPlacementNorth;
        this.itemPlacementWest = itemPlacementWest;
        this.itemPlacementSouth = itemPlacementSouth;
        this.terrorist = terrorist;
        this.terroristPlacement = terroristPlacement;
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

    public String getNorthDescription() {
        return northDescription;
    }

    public String getWestDescription() {
        return westDescription;
    }

    public String getEastDescription() {
        return eastDescription;
    }

    public String getSouthDescription() {
        return southDescription;
    }

    public Items getItem() {
        return item;
    }

    public int getItemPlacementSouth() {
        return itemPlacementSouth;
    }

    public int getItemPlacementEast() {
        return itemPlacementEast;
    }

    public int getItemPlacementWest() {
        return itemPlacementWest;
    }

    public int getItemPlacementNorth() {
        return itemPlacementNorth;
    }

    public Terrorist getTerrorist() {
        return terrorist;
    }

    public int getTerroristPlacement() {
        return terroristPlacement;
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

    public void setNorthDescription(String northDescription) {
        this.northDescription = northDescription;
    }

    public void setWestDescription(String westDescription) {
        this.westDescription = westDescription;
    }

    public void setEastDescription(String eastDescription) {
        this.eastDescription = eastDescription;
    }

    public void setSouthDescription(String southDescription) {
        this.southDescription = southDescription;
    }

    public void setItemPlacementSouth(int itemPlacementSouth) {
        this.itemPlacementSouth = itemPlacementSouth;
    }

    public void setItemPlacementEast(int itemPlacementEast) {
        this.itemPlacementEast = itemPlacementEast;
    }

    public void setItemPlacementWest(int itemPlacementWest) {
        this.itemPlacementWest = itemPlacementWest;
    }

    public void setItemPlacementNorth(int itemPlacementNorth) {
        this.itemPlacementNorth = itemPlacementNorth;
    }

    public void setItem(Items item) {
        this.item = item;
    }

    public void setTerrorist(Terrorist terrorist) {
        this.terrorist = terrorist;
    }

    public void setTerroristPlacement(int terroristPlacement) {
        this.terroristPlacement = terroristPlacement;
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
                ", item=" + item +
                ", itemPlacementSouth=" + itemPlacementSouth +
                ", itemPlacementEast=" + itemPlacementEast +
                ", itemPlacementWest=" + itemPlacementWest +
                ", itemPlacementNorth=" + itemPlacementNorth +
                '}';
    }

}

