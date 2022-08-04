package com.ellane.model;

public class floors {
    private String firstFloor;
    private String secondFloor;
    private String thirdFloor;
    private String forthFloor;
    private String fifthFloor;

    public floors(String firstFloor, String secondFloor, String thirdFloor, String forthFloor, String fifthFloor) {
        this.firstFloor = firstFloor;
        this.secondFloor = secondFloor;
        this.thirdFloor = thirdFloor;
        this.forthFloor = forthFloor;
        this.fifthFloor = fifthFloor;
    }

    public String getFirstFloor() {
        return firstFloor;
    }

    public void setFirstFloor(String firstFloor) {
        this.firstFloor = firstFloor;
    }

    public String getSecondFloor() {
        return secondFloor;
    }

    public void setSecondFloor(String secondFloor) {
        this.secondFloor = secondFloor;
    }

    public String getThirdFloor() {
        return thirdFloor;
    }

    public void setThirdFloor(String thirdFloor) {
        this.thirdFloor = thirdFloor;
    }

    public String getForthFloor() {
        return forthFloor;
    }

    public void setForthFloor(String forthFloor) {
        this.forthFloor = forthFloor;
    }

    public String getFifthFloor() {
        return fifthFloor;
    }

    public void setFifthFloor(String fifthFloor) {
        this.fifthFloor = fifthFloor;
    }
}