package model;

import model.Piece;

/**
 * Created by Patrick on 12/6/2016.
 */
public class Queen extends Piece {
    public Queen(Integer[] location){ super("Queen",location, "queen.png");}

    @Override
    public boolean validMove(Integer[] moveTo) {
        int rowChange = Math.abs(getLocation()[0]- moveTo[0]);
        int colChange = Math.abs(getLocation()[1]- moveTo[1]);

        if(rowChange==colChange)
            return true;
        else if (rowChange!=0 && colChange==0)
            return true;
        else if(colChange!=0 && rowChange==0)
            return true;
        else
            return false;
    }
}
