package com.mj.fooddeliveryapp;

public class cartmodel {
    private String foodname;
    public int foodprice;
    public String address;
    public  int times;
    public  String foodtype;
    public cartmodel(String foodname,int foodprice,String address,int times,String foodtype)
    {
        this.foodname=foodname;
        this.foodprice=foodprice;
        this.address=address;
        this.times=times;
        this.foodtype=foodtype;
    }

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public int getFoodprice() {
        return foodprice;
    }

    public void setFoodprice(int foodprice) {
        this.foodprice = foodprice;
    }

    public String getAddress() {
        return address;
    }

    public String getFoodtype() {
        return foodtype;
    }

    public void setFoodtype(String foodtype) {
        this.foodtype = foodtype;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
