package model.application;

import model.race.Category;
import model.user.Racer;
import model.user.UserContext;

public class EntranceModel {

    private UserContext userType;

    public boolean validateUserAccount() {
        return userType != null;
    }

    public UserContext signUp(String name, String ccInfo) {
        Racer racer = new Racer(ccInfo, name);
        Category.attachRacer(racer);
        userType = new UserContext(racer);
        return userType;
    }

    public void setUserType(UserContext userType) {
        this.userType = userType;
    }

    public UserContext getUserType() {
        return userType;
    }
}
