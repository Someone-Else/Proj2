package model;

import model.Piece;

/**
 * Created by Patrick on 12/6/2016.
 */
public class Empty extends Piece {
    public Empty(Integer[] location){ super("Empty",location,"light.png");}
    @Override
    public boolean validMove(Integer[] moveTo) {
        return false;
    }

}
