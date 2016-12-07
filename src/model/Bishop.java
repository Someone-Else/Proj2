package model;

import model.Piece;

/**
 * Created by Patrick on 12/6/2016.
 */
public class Bishop extends Piece {
    Bishop(Integer[] location){ super("Bishop",location,"bishop.png");}

    @Override
    public boolean validMove(Integer[] moveTo) {
        int rowChange = Math.abs(getLocation()[0]- moveTo[0]);
        int colChange = Math.abs(getLocation()[1]- moveTo[1]);

        if(rowChange==colChange)
            return true;
        else
            return false;
    }
}
