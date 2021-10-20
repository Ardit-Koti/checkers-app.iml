package com.webcheckers.model;

public class Space {

    public enum shade {LIGHT, DARK}
    public enum Status {VALID, OCCUPIED, INVALID}
    private Status status;
    private shade shade;
    private Piece currentPiece;
    private int index;

    public Space(Piece currentPiece, int index) {
        this.index = index;
        if(currentPiece != null) {
            this.currentPiece = currentPiece;
            this.status = Status.OCCUPIED;

        } else{
            this.currentPiece = null;
            this.status = Status.VALID;
        }
    }

    public Space(int index, Status state) {
        this.index = index;
        this.currentPiece = null;
        this.status = state;
    }

    public int getIndex() {
        return index;
    }

    public void setShade(Space.shade shade) {
        this.shade = shade;
    }

    public Piece getCurrentPiece() {
        return currentPiece;
    }

    public boolean moveTo (Space origin){
        if(origin == null) {
            return false;
        }
        else if(status != Status.VALID) {
            return false;
        }
        else if(origin.getCurrentPiece() == null) {
            return false;
        }

        addCurrentPiece(origin.getCurrentPiece());
        origin.removeCurrentPiece();
        return true;
    }

    public Status addCurrentPiece(Piece pieceHelper) {
        if(status == Status.VALID) {
            currentPiece = pieceHelper;
            status = Status.OCCUPIED;
            return status;
        } else{
            return status;
        }
    }

    public Status removeCurrentPiece() {
        if (status == Status.OCCUPIED) {
            currentPiece = null;
            status = Status.VALID;
            return status;
        } else {
            return status;
        }
    }

    public boolean isNotTaken() {
        return (status != Status.INVALID && status != Status.OCCUPIED);
    }

    public boolean isTaken() {
        return (this.status == status.OCCUPIED);
    }

    public boolean isAllowed() {
        return(this.status == Status.VALID);
    }

    public Status getState() {
        return this.status;
    }





}