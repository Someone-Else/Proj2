package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Scanner;

/**
 * Created by Patrick on 12/6/2016.
 */
public class Board extends Observable {

    private Board board;
    private int height;
    private int width;
    private int moves = 0;
    private int SIZE_W= 4;
    private int SIZE_H= 4;
    private char[][] boardArray = new char[SIZE_W][SIZE_H];
    private ArrayList<Piece> pieceList = new ArrayList<>();



    public Board(String file) throws FileNotFoundException{
        readFile(file);
        this.height = boardArray.length;
        this.width = boardArray[0].length;
    }

    private void readFile(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner in = new Scanner(file);

        for(int i=0; i<SIZE_H;i++){
            String line = in.nextLine();
            String[] temp = line.split(" ");
            for (int j=0; j<SIZE_W; j++){
                boardArray[i][j]=temp[j].charAt(0);
                if(boardArray[i][j]!='-')
                    addPiece(boardArray[i][j],i,j);
            }
        }
    }

    private void addPiece(char type, int row, int col){
        Piece piece;
        Integer[] loc = new Integer[2];
        loc[0]=row;
        loc[1]=col;
        switch (type){
            case 'K':
                piece = new King(loc);
            case 'B':
                piece = new Bishop(loc);
            case 'N':
                piece = new Knight(loc);
            case 'P':
                piece = new Pawn(loc);
            case 'R':
                piece = new Rook(loc);
            case 'Q':
                piece = new Queen(loc);
            default:
                piece = new Empty(loc);
        }
        pieceList.add(piece);
    }

    public char[][] getBoardArray() {
        return boardArray;
    }

    public void move(Piece move, Piece take){
        Integer[] loc = take.getLocation();
        if(boardArray[loc[0]][loc[1]]!='-') {
            if (move.validMove(loc)) {
                Integer[] temp = move.getLocation();
                pieceList.remove(take);
                move.setLocation(loc);
                boardArray[temp[0]][temp[1]]='-';
            }
        }
        update();

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
