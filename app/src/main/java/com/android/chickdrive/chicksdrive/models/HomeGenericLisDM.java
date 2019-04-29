package com.android.chickdrive.chicksdrive.models;

import com.google.gson.annotations.SerializedName;

public class HomeGenericLisDM {


    @SerializedName("Days_Age")
    private int daysAge;

    @SerializedName("WeightMin")
    private int weightMin;

    @SerializedName("WeightMax")
    private int weightMax;

    @SerializedName("Image3")
    private String image3;

    @SerializedName("Image2")
    private String image2;

    @SerializedName("Image1")
    private String image1;

    @SerializedName("Quantity")
    private int quantity;

    @SerializedName("ID")
    private int iD;

    @SerializedName("City")
    private String city;

    public String getChickenType() {
        return chickenType;
    }

    public void setChickenType(String chickenType) {
        this.chickenType = chickenType;
    }

    @SerializedName("ChickenType")
    private String chickenType;

    public int getDaysAge() {
        return daysAge;
    }

    public void setDaysAge(int daysAge) {
        this.daysAge = daysAge;
    }

    public int getWeightMin() {
        return weightMin;
    }

    public void setWeightMin(int weightMin) {
        this.weightMin = weightMin;
    }

    public int getWeightMax() {
        return weightMax;
    }

    public void setWeightMax(int weightMax) {
        this.weightMax = weightMax;
    }

    public String getImage3() {
        return image3;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getiD() {
        return iD;
    }

    public void setiD(int iD) {
        this.iD = iD;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
