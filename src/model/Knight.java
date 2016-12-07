package model;

import model.Piece;

/**
 * Created by Patrick on 12/6/2016.
 */
public class Knight extends Piece {
    public Knight(Integer[] location){ super("Knight",location, "knight.png");}

    @Override
    public boolean validMove(Integer[] moveTo) {
        int rowChange = Math.abs(getLocation()[0]- moveTo[0]);
        int colChange = Math.abs(getLocation()[1]- moveTo[1]);

        if(rowChange==1 && colChange==2)
            return true;
        else if(rowChange==2 && colChange==1)
            return true;
        return false;
    }
}
