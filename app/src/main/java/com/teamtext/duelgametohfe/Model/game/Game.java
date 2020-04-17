package com.teamtext.duelgametohfe.Model.game;



public class Game {

    public int id;
    public String name;
    public int resourceImage;
    public Class aClass;

    public void setaClass(Class aClass) {
        this.aClass = aClass;
    }

    public Class getaClass() {
        return aClass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getResourceImage() {
        return resourceImage;
    }

    public void setResourceImage(int resourceImage) {
        this.resourceImage = resourceImage;
    }




}
