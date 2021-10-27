package com.webcheckers.model;

public class Space {

    public enum Shade {LIGHT, DARK}
    public enum Status {VACANT, OCCUPIED, INVALID}
    private Status status;
    private Shade shade;

    private Piece piece;
    private int cellIdx;
    private final int limit = 7;

//    public Space(Piece currentPiece, int index) {
//        this.cellIdx = index;
//        if(currentPiece != null) {
//            this.currentPiece = currentPiece;
//            this.status = Status.OCCUPIED;
//
//        } else{
//            this.currentPiece = null;
//            this.status = Status.VALID;
//        }
//    }

//    public Space(int index, Status state) {
//        this.cellIdx = index;
//        this.currentPiece = null;
//        this.status = state;
//    }

    public Space(int cellIdx, int row_index)
    {
        this.cellIdx = cellIdx;
        if(row_index%2 ==0)
        {
            if(this.cellIdx%2 ==0)
            {
                this.shade = Shade.DARK;
            }
            else this.shade = Shade.LIGHT;
        }
        else if(row_index%2 == 1)
        {
            if(this.cellIdx%2 == 0)
            {
                this.shade = Shade.LIGHT;
            }
            else this.shade = Shade.DARK;
        }
        if(this.shade == Shade.DARK && row_index >= 0 && row_index <= 2)
        {
            addCurrentPiece(new Piece(Piece.Variety.REGULAR, Piece.Color.White, Piece.State.Alive));
        }
        else if(this.shade == Shade.DARK && row_index >= 5 && row_index <= 7)
        {
            addCurrentPiece(new Piece(Piece.Variety.REGULAR, Piece.Color.Red, Piece.State.Alive));
        }
    }


    public int getCellIdx() { return cellIdx; }

    public void setShade(Space.Shade shade) {this.shade = shade; }

    public boolean isValid()
    {
        return this.shade == Shade.DARK && this.status == Status.VACANT;
    }

    public Shade getShade(){
        return shade;
    }

    public Piece getPiece() {
        return piece;
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
        if(status == Status.VACANT) {
            this.piece = pieceHelper;
            status = Status.OCCUPIED;
            return status;
        } else{
            return status;
        }
    }

    public Status removeCurrentPiece() {
        if (status == Status.OCCUPIED) {
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