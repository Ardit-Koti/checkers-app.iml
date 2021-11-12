package com.webcheckers.model;

public class Space {

    public enum Shade {LIGHT, DARK}
    public enum Status {VACANT, OCCUPIED, INVALID}
    private Status status;
    private Shade shade;

    @Override
    public String toString() {
        return "Space{" +
                "piece=" + piece +
                ", cellIdx=" + cellIdx +
                '}';
    }

    public Piece piece = null;
    private int cellIdx;
    private final int limit = 7;

    public Space(int cellIdx, int row_index)
    {
        this.cellIdx = cellIdx;
        if(row_index%2 ==0)
        {
            if(this.cellIdx%2 ==0)
            {
                this.shade = Shade.LIGHT;
                this.status = Status.VACANT;
            }
            else this.shade = Shade.DARK;
            this.status = Status.VACANT;
        }
        else if(row_index%2 == 1)
        {
            if(this.cellIdx%2 == 0)
            {
                this.shade = Shade.DARK;
                this.status = Status.VACANT;
            }
            else this.shade = Shade.LIGHT;
            this.status = Status.VACANT;
        }
        if(this.shade.equals(Shade.DARK) && row_index >= 5 && row_index <= 7)
        {
            addCurrentPiece(new Piece(Piece.type.SINGLE, Color.RED));
        }
        else if(this.shade.equals(Shade.DARK) && row_index >= 0 && row_index <= 2)
        {
            addCurrentPiece(new Piece(Piece.type.SINGLE, Color.WHITE));
        }
    }


    public int getCellIdx() { return cellIdx; }

    public void setShade(Space.Shade shade) {this.shade = shade; }

    public boolean isValid()
    {
        return this.shade.equals(Shade.DARK) && this.status.equals(Status.VACANT);
    }

    public Shade getShade(){
        return shade;
    }

    public Piece getPiece() {
        return piece;
    }

    public Boolean hasPiece(){
        if (this.piece == null)
            return false;
        return true;
    }

    public boolean moveTo (Space origin){
        if(origin == null) {
            return false;
        }
        else if(status != Status.VACANT) {
            return false;
        }
        else if(origin.getPiece() == null) {
            return false;
        }

        addCurrentPiece(origin.getPiece());
        origin.removeCurrentPiece();
        return true;
    }

    public Status addCurrentPiece(Piece pieceHelper) {
        if(status.equals(Status.VACANT)) {
            this.piece = pieceHelper;
            status = Status.OCCUPIED;
            return status;
        } else{
            return status;
        }
    }

    public Status removeCurrentPiece() {
        if (status.equals(Status.OCCUPIED)) {
            this.piece = null;
            status = Status.VACANT;
            return status;
        } else {
            return status;
        }
    }

    public boolean isNotTaken() {
        return (status != Status.INVALID && status != Status.OCCUPIED);
    }

    public boolean isTaken() {
        return (this.status == Status.OCCUPIED);
    }

    public boolean isAllowed() {
        return(this.status == Status.VACANT);
    }

    public Status getState() {
        return this.status;
    }





}