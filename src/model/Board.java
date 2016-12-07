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
        Integer[] loc = new Integer[2];
        loc[0]=col;
        loc[1]=row;
        Piece piece;
        switch (type){
            case 'B':
                piece = new Bishop(loc);
                break;
            case 'K':
                piece = new King(loc);
                break;
            case 'N':
                piece = new Knight(loc);
                break;
            case 'P':
                piece = new Pawn(loc);
                break;
            case 'R':
                piece = new Rook(loc);
                break;
            case 'Q':
                piece = new Queen(loc);
                break;
            default:
                piece = new Empty(loc);
        }
        pieceList.add(piece);
    }

    public Piece getPiece(int row, int col){
        Integer[] loc = new Integer[2];
        loc[0]=row;
        loc[1]=col;
        for (Piece piece :
                pieceList) {
            if(piece.getLocation()[0]==loc[0]&&piece.getLocation()[1]==loc[1]){
                return piece;
            }
        }
        return null;
    }

    public String getImage(char type) {
        for (Piece piece :
                pieceList) {
            if(piece.toString().equalsIgnoreCase(Character.toString(type))){
                return piece.getImage();
            }
        }
        return null;
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
                boardArray[loc[0]][loc[1]]=move.toString().charAt(0);
                moves++;
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
