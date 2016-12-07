package model;

import model.Piece;

/**
 * Created by Patrick on 12/6/2016.
 */
public class King extends Piece {
    public King(Integer[] location){ super("King",location, "king.png");}

    @Override
    public boolean validMove(Integer[] moveTo) {
        int rowChange = Math.abs(getLocation()[0]- moveTo[0]);
        int colChange = Math.abs(getLocation()[1]- moveTo[1]);

        if(rowChange>1 || colChange>1 || rowChange+colChange==0)
            return false;
        else
            return true;
    }
}
