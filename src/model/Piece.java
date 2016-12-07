package model;


/**
 * Created by Patrick on 12/5/2016.
 */
public abstract class Piece {
    private String image;
    private String type;
    private boolean taken;
    private Integer[] location;
    private boolean isSelected = false;

    public Piece(String type,Integer[] location, String image){
        this.type= type;
        this.location= location;
        this.taken = false;
        this.image = image;
    }

    public String getType(){ return type;}
    public boolean getTaken(){ return taken;}
    public Integer[] getLocation(){ return location;}
    public boolean getIsSelected(){ return isSelected;}
    public String getImage(){return "src\\gui\\resources\\"+image;}
    public void setSelected(){
        if(isSelected)
            isSelected=false;
        else
            isSelected=true;
    }
    public void setLocation(Integer[] location){this.location=location;}

    public abstract boolean validMove(Integer[] moveTo);
}

