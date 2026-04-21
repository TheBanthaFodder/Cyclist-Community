package Model;

import java.util.ArrayList;

public class Racer extends User{
    private String cardInfo;
    private int category;
    private ArrayList<Race> RacesAttended;
    private int totalWins;

    public void signUp(){}


    /**Set this to override User.getData(),
        since a racer has a lot of possible information
        -mehurzel
    */
    @Override
    public void getData(){}

}
