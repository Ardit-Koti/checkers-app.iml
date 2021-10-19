package com.webcheckers.model;

public class Space {
    private boolean isTaken;
    public enum shade {light, dark}
    private shade shade;
    private Piece piece;


    public Space(){
        this.isTaken = false;
        this.piece = piece;
    }


    public void setShade(Space.shade shade) {
        this.shade = shade;
    }

    public void moveTo(){
        this.isTaken=true;
    }

    public void moveAway(){
        this.isTaken=false;
    }

    public boolean isTaken() {
        return isTaken;
    }

    public void setTaken(boolean valid_square) {
        this.isTaken = valid_square;
    }
}
