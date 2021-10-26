package com.webcheckers.model;

import java.util.Iterator;

public class Row implements Iterable{
    private int index;
    private final int limit = 7;
    private int i =0;

    public Row(int index){ this.index = index;}
    public int getIndex(){return this.index;}

    @Override
    public Iterator<Space> iterator() {
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return i++ <= limit;
            }

            @Override
            public Space next() {
                return new Space(i, index);
            }
        };
    }
}
