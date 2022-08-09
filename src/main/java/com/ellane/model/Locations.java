package com.ellane.model;


public class Locations {

        private String name;
        private String description;
        private String north;
        private String west;
        private String east;
        private String south;
        private Items item;
        private int itemPlacementSouth;
        private int itemPlacementEast;
        private int itemPlacementWest;
        private int itemPlacementNorth;

        public Locations () {
        }

        public Locations(Items item, String description, String west, String south, String north, String east, String name, int itemPlacementEast, int itemPlacementNorth, int itemPlacementSouth, int itemPlacementWest) {
            this.item = item;
            this.description = description;
            this.west = west;
            this.south = south;
            this.north = north;
            this.east = east;
            this.name = name;
            this.itemPlacementEast = itemPlacementEast;
            this.itemPlacementNorth = itemPlacementNorth;
            this.itemPlacementWest = itemPlacementWest;
            this.itemPlacementSouth = itemPlacementSouth;
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

