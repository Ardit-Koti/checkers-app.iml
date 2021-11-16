package com.webcheckers.model;

import com.webcheckers.application.DevMode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Row{
    private final int index;
    private final int redlimit = 7;
    private final int whitelimit = 0;

    public ArrayList<Space> spaceList;

    // to reverse board, create copy of board in reversed state


    @Override
    public String toString() {
        return "Row{" +
                "spaceList=" + spaceList +
                '}';
    }

    public Row(int index)
    {
        spaceList = new ArrayList<>();
        this.index = index;
        for(int a = 0; a<8; a++)
        {   //devmode determines the board start arrangement
            spaceList.add(new Space(a, index, DevMode.NONE));
        }
    }

    public Row(int index, ArrayList<Space> spaces){
        this.index = index;
        this.spaceList = spaces;
    }

    public int getIndex(){return this.index;}


    public Iterator<Space> iterate_by_color(boolean color) {
        if(color) {
            return new Iterator<Space>() {
                int i = -1;
                @Override
                public boolean hasNext() {
                    return i+1 <= redlimit;
                }

                @Override
                public Space next() {
                    i= i+1;
                    return spaceList.get(i);
                }
            };
        }
        else{
            return new Iterator<Space>() {
                int a = 8;
                @Override
                public boolean hasNext() {
                    return a-1 >= whitelimit;
                }

                @Override
                public Space next() {
                    a = a-1;
                    return spaceList.get(a);
                }
            };
        }
    }

    public List<Space> getSpaces() {
        return spaceList;
    }

    /**
     * Gets the piece at the given column.
     *
     * @param column the column or index that the piece is at.
     * @return the desired piece.
     */
    public Piece getPieceAtIndex(int column) {
        return spaceList.get(column).getPiece();
    }

    public Row copyRow(){
        ArrayList<Space> copySpaces = new ArrayList<>();
        for (Space s: spaceList){
            copySpaces.add(s.copySpace());
        }
        return new Row(getIndex(), copySpaces);
    }
}
