package com.mj.fooddeliveryapp;

public class Foodmenu {
    private  String foodname;
    private  String price;
    private String foodtype;
  public Foodmenu()
  {

  }

    public Foodmenu(String foodname,String price, String foodtype)
    {
    this.foodname=foodname;
    this.foodtype=foodtype;
    this.price=price;

    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getFoodtype() {
        return foodtype;
    }

    public void setFoodtype(String foodtype) {
        this.foodtype = foodtype;
    }


    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }


    public String getPrice() {
        return price;
    }



}
