package com.webcheckers.model;

import java.util.ArrayList;
import java.util.Iterator;

public class Row implements Iterable{
    private final int index;
    private final int limit = 7;
    private int i =-1;
    public ArrayList<Space> spaceList;

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

    @Override
    public Iterator<Space> iterator() {
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return i+1 <= limit;
            }

            @Override
            public Space next()
            {
                i = i+1;
                return spaceList.get(i);
            }
        };
    }
}
