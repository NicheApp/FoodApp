package com.mj.fooddeliveryapp;

public class cartmodel {
    private String foodname;
    private int foodprice;
    public String address;
    public cartmodel(String foodname,int foodprice,String address)
    {
        this.foodname=foodname;
        this.foodprice=foodprice;
        this.address=address;
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

    public void setAddress(String address) {
        this.address = address;
    }
}
