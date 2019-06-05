package com.maria.travelagency.entity;

public enum Transport {

    PLANE("Самолет"),
    
    BOAT("Корабль"),
    
    TRAIN("Поезд"),
    
    BUS("Автобус");

    private String name;
    

    Transport(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
