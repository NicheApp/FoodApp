package com.mj.fooddeliveryapp;

public class Foodmenu {
    private  String foodname;
    private  String foodimage;
    private  int time ;
    private  int distance ;
    private  int foodimage2 ;
  public Foodmenu()
  {


  }

    public Foodmenu(String foodname,String foodimage ,int time, int distance,int foodimage2)
    {
    this.foodname=foodname;
    this.time=time;
    this.distance=distance;
    this.foodimage=foodimage;
    this.foodimage2=foodimage2;

    }

    public int getFoodimage2() {
        return foodimage2;
    }

    public void setFoodimage2(int foodimage2) {
        this.foodimage2 = foodimage2;
    }

    public String getFoodimage() {
        return foodimage;
    }

    public void setFoodimage(String foodimage) {
        this.foodimage = foodimage;
    }

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}