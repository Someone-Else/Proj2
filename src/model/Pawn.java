package model;

import model.Piece;

/**
 * Created by Patrick on 12/6/2016.
 */
public class Pawn extends Piece {
    public Pawn(Integer[] location){ super("Pawn",location, "pawn.png");}

    @Override
    public boolean validMove(Integer[] moveTo) {
        int rowChange = (getLocation()[0]- moveTo[0]);
        int colChange = Math.abs(getLocation()[1]- moveTo[1]);

        if(rowChange==-1 && colChange==1)
            return true;
        else
            return false;
    }
}
