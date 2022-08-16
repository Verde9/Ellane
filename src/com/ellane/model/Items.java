package com.ellane.model;


public class Items {
    private String name;
    private String item_description;

    public Items(String name) {
        this.name = name;
    }

    public Items(String name, String item_status) {
        this.name = name;
        this.item_description = item_status;
    }

    public String getName() {
        return name;
    }

    public String getItem_description() {
        return item_description;
    }


    @Override
    public String toString() {
        return "Items{" +
                "item='" + name + '\'' +
                ", item_status='" + item_description + '\'' +
                '}';
    }
}