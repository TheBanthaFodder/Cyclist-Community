package controller;

import model.application.EntranceModel;
import model.user.UserContext;
import view.EntranceView;

public class EntranceController {

    private final EntranceModel model;
    private final EntranceView view;

    public EntranceController() {
        this(new EntranceModel(), new EntranceView());
    }

    public EntranceController(EntranceModel model, EntranceView view) {
        this.model = model;
        this.view = view;
    }

    public void handleLogin() {
        if (!model.validateUserAccount()) {
            view.addError("No valid user account is available.");
            handleErrors();
            return;
        }
        view.viewLogin();
    }

    public void handleSignUp() {
        String name = view.getUserInput("Enter racer name: ");
        String ccInfo = view.getUserInput("Enter credit card info: ");
        model.signUp(name, ccInfo);
        view.viewSignUp();
    }

    public UserContext handleUser() {
        return sendUserType();
    }

    public UserContext sendUserType() {
        return model.getUserType();
    }

    public void handleErrors() {
        view.viewErrors();
    }

    public void setUserType(UserContext userType) {
        model.setUserType(userType);
    }
}
