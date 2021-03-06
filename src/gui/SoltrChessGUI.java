/**
 * Part of SoltrChessLayout project.
 * Created 10 2015
 *
 * @author James Heliotis
 */

package gui;

import javafx.application.Application;
import model.*;
/**
 * A miniature chess board
 *
 * @author James Heliotis
 */
 /**
 * Created by Patrick on 12/3/2016.
 */
import javafx.event.*;
import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.Piece;

import java.awt.Label;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;



public class SoltrChessGUI extends Application implements Observer {


    private int SIZE_W= 4;
    private int SIZE_H= 4;
    private Button[][] location = new Button[SIZE_W][SIZE_H];
    private char[][] loc = new char[SIZE_W][SIZE_H];
    private Piece selected = null;
    private Board board;
    private ArrayList<Button> buttonList= new ArrayList<Button>();


    public SoltrChessGUI(){
        super();
    }

    @Override
    public void update(Observable o, Object arg) {
        loc=board.getBoardArray();
        Integer[] location = new Integer[2];
        GridPane boardScene = new GridPane();
        for(Button b: buttonList){
            location[1]=buttonList.indexOf(b)/SIZE_W;
            location[0]=buttonList.indexOf(b)%SIZE_H;
            Image image = new Image("File:"+board.getImage(loc[location[0]][location[1]]));
            b.setGraphic(new ImageView(image));
        }

        for (char[] row:loc) {
            for (char id:
                    row) {
                System.out.print(id+" ");
            }
            System.out.println();
        }
    }



    public void start(Stage stage) throws FileNotFoundException{
        stage.setTitle("Solitaire Chess");
        BorderPane border = new BorderPane();

        this.board=new Board("config");
        board.addObserver(this);
        loc=board.getBoardArray();

        border.setCenter(board());
        border.setTop(title());
        border.setBottom(buttonBar());

        Scene scene = new Scene(border);
        stage.setScene(scene);
        stage.sizeToScene();


        for (char[] row:loc) {
            for (char id:
                    row) {
                System.out.print(id+" ");
            }
            System.out.println();
        }

        stage.show();
    }

    public void init() throws Exception{
        super.init();

    }


    @Override
    public void stop() throws Exception {
        super.stop();
    }

    public GridPane board(){
        GridPane boardScene = new GridPane();
        for(int i=0; i<SIZE_H; i++){
            for(int j=0;j<SIZE_W; j++){
                Button button= boardButton(i,j);
                boardScene.add(button, i, j);
                location[i][j]=button;
                buttonList.add(button);
            }
        }
        return boardScene;
    }

    private VBox title(){
        VBox title = new VBox();
        //Label test = new Label();
        //test.setText("test");


        return title;
    }

    private Label test(){
        Label test = new Label();
        test.setText("test");
        return test;
    }

    private HBox buttonBar(){
        Button newGame = new Button("New Game");
        Button restart = new Button("Restart");
        Button hint = new Button("Hint");
        Button solve = new Button("Solve");

        HBox bar = new HBox();
        bar.getChildren().addAll(newGame, restart, hint, solve);
        bar.setAlignment(Pos.CENTER);
        return bar;
    }

    private Button boardButton(int col, int row){
        Button button = new Button();
        Image white = new Image("File:src\\gui\\resources\\white.png");
        Image dark = new Image("File:src\\gui\\resources\\dark.png");

        button.setPrefSize(150,150);
        if (row%2==0) {
            if (col % 2 == 1) {
                button.setStyle("-fx-background-color: #ffffff");
            } else
                button.setStyle("-fx-background-color: #000000");
        }
        else {
            if (col % 2 == 1) {
                button.setStyle("-fx-background-color: #000000");
            } else
                button.setStyle("-fx-background-color: #ffffff");
        }
        button.setText(col+", "+row);
        if(loc[row][col]!='-'){
            Image image = new Image("File:"+board.getImage(loc[row][col]));
            button.setGraphic(new ImageView(image));
            button.setOnAction(new pieceHandler(board.getPiece(row,col)));
        }
        else{
            Integer[] temp = new Integer[2];
            temp[0]=row;
            temp[1]=col;
            Empty none = new Empty(temp);
            button.setOnAction(new pieceHandler(none));
        }
        return button;
    }

    private class pieceHandler implements EventHandler{
        private Piece piece;

        public pieceHandler(Piece piece){
            this.piece = piece;
        }

        @Override
        public void handle(Event event) {
            if(selected==null){
                this.piece.setSelected();
                if(!piece.getIsSelected()) {
                    selected = piece;
                    System.out.print("success");
                }
            }
            else {
                board.move(selected, piece);
                selected.setSelected();
                selected=null;
            }
        }
    }


    private class ResetHandler implements EventHandler{
        @Override
        public void handle(Event event) {

        }
    }

    private class NewGameHandler implements EventHandler{
        @Override
        public void handle(Event event) {

        }
    }

    private class HintHandler implements EventHandler{
        @Override
        public void handle(Event event) {

        }
    }

    private class SolveHandler implements EventHandler{
        @Override
        public void handle(Event event) {

        }
    }

    public static void main( String[] args){
        Application.launch(args);
    }
}


