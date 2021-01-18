package com.mj.fooddeliveryapp;

public class  restaurantmodelclass {
    private  String restaurantname;
    private  String restaurantimage;
    private  int time ;
    private  Double distance ;
    private  int resid;
    private  int rating;

    public restaurantmodelclass()
    {


    }

    public restaurantmodelclass(String restaurantname,String restaurantimage , int time,Double distance,int resid,int rating)
    {
       this.restaurantname=restaurantname;
       this.restaurantimage=restaurantimage;
       this.time=time;
       this.distance=distance;
       this.resid=resid;
       this.rating=rating;

    }

    public String getRestaurantname() {
        return restaurantname;
    }

    public void setRestaurantname(String restaurantname) {
        this.restaurantname = restaurantname;
    }

    public String getRestaurantimage() {
        return restaurantimage;
    }

    public void setRestaurantimage(String restaurantimage) {
        this.restaurantimage = restaurantimage;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public int getResid() {
        return resid;
    }

    public void setResid(int resid) {
        this.resid = resid;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
