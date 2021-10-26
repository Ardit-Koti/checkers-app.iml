package com.webcheckers.model;

import java.util.Iterator;

public class Board implements Iterable{
    private final int limit = 7;
    private int i = 0;


    @Override
    public Iterator<Row> iterator() {
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return i++<=7;
            }

            @Override
            public Row next() {
                return new Row(i);
            }
        };
    }
}
