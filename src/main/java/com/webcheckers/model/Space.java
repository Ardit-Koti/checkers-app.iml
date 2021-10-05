package com.webcheckers.model;

public class Space {
    public Space(){

    }
    private boolean isTaken;
    // TODO: 10/4/21 convert to enum
    public enum shade {light, dark}
    private shade shade;

    public void setShade(Space.shade shade) {
        this.shade = shade;
    }
}
