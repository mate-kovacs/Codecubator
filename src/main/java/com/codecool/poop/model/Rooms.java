package com.codecool.poop.model;

public enum Rooms {
    WHITE_ROOM ("white-room"),
    GREEN_ROOM ("green-room"),
    BLUE_ROOM ("blue-room"),
    RED_ROOM ("red-room");

    public String name;

    Rooms(String name){
        this.name = name;
    }
}
