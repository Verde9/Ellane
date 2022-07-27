package com.ellane.model;

public class Items {

    private String item;
    private String item_status;
    private String item2;
    private String item_status2;

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
        return "Items{" +
                "item='" + item + '\'' +
                ", item_status='" + item_status + '\'' +
                ", item2='" + item2 + '\'' +
                ", item_status2='" + item_status2 + '\'' +
                '}';
    }
}