package com.webcheckers.model;

public class Space {
    public Space(){

    }
    private boolean isTaken;
    public enum shade {light, dark}
    private shade shade;

    public void setShade(Space.shade shade) {
        this.shade = shade;
    }
    public void moveTo(){
        this.isTaken=true;
    }
    public void moveAway(){
        this.isTaken=false;
    }
}
