package com.webcheckers.model;

import java.util.ArrayList;
import java.util.Iterator;

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
        {
            spaceList.add(new Space(a, index));
        }
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
}
