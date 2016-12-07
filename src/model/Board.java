package model;

import java.util.Observable;

/**
 * Created by Patrick on 12/6/2016.
 */
public class Board extends Observable {

    private Board board;
    private int height;
    private int width;
    private int moves = 0;
    private char[][] boardArray;

    public Board(char[][] boardArray){
        this.boardArray = boardArray;
        this.height = boardArray.length;
        this.width = boardArray[0].length;
    }



    public void update(){
        setChanged();
        notifyObservers();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }


}
