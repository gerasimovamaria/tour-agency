package com.maria.travelagency.entity;

public enum TourType {

    VACATIONS("vacations"),
    

    TRIPS("trips"),
    

    SHOPPINGS("shoppings");


    private String name;
    
    TourType(String name){
        this.name = name;
    }


    public String getName() {
        return name;
    }
}
