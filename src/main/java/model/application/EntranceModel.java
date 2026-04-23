package model.application;

import model.user.Racer;
import model.user.UserContext;

public class EntranceModel {

    private UserContext userType;

    public boolean validateUserAccount() {
        return userType != null;
    }

    public UserContext signUp(String name, String ccInfo) {
        userType = new UserContext(new Racer(ccInfo, name));
        return userType;
    }

    public void setUserType(UserContext userType) {
        this.userType = userType;
    }

    public UserContext getUserType() {
        return userType;
    }
}
