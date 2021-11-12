package com.webcheckers.model;

import java.lang.reflect.Array;
import java.util.*;

public class Board{
    private final int redlimit = 7;
    private final int whitelimit = 0;

    public ArrayList<Row> RowList;
    private List<Move> moves = new ArrayList<>();


    @Override
    public String toString() {
        return "Board{" +
                "RowList=" + RowList +
                '}';
    }
    public Iterator<Row> iterate_by_color(boolean color) {
        if(color) {
            return new Iterator<Row>() {
                int i = -1;
                @Override
                public boolean hasNext() {
                    return i+1 <= redlimit;
                }

                @Override
                public Row next() {
                    i= i+1;
                    return RowList.get(i);
                }
            };
        }
        else{
            return new Iterator<Row>() {
                int a = 8;
                @Override
                public boolean hasNext() {
                    return a-1 >= whitelimit;
                }

                @Override
                public Row next() {
                    a = a-1;
                    return RowList.get(a);
                }
            };
        }
    }

  
    public Board(){
        RowList = new ArrayList<>();
        for(int a = 0; a < 8; a++)
        {
            RowList.add(new Row(a));
        }


    }

    public void addMove(Move m){
        moves.add(m);
    }
    public List<Move> getMoves(){
        return moves;
    }

    }
