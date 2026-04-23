package controller;

import model.application.RacerModel;
import model.user.UserContext;
import view.RacerView;

public class RacerController {

    private final RacerModel model;
    private final RacerView view;

    public RacerController() {
        this(new RacerModel(), new RacerView());
    }

    public RacerController(RacerModel model, RacerView view) {
        this.model = model;
        this.view = view;
    }

    public void handleSystemSignUp() {
        String name = view.getUserInput("Enter racer name: ");
        String ccInfo = view.getUserInput("Enter credit card info: ");
        model.signUp(name, ccInfo);
        view.viewSystemSignUp();
    }

    public void handleBuyLicense() {
        model.buyLicense();
        view.viewBuyLicense();
    }

    public void handleRaceRegistration() {
        // TODO
        view.viewRaceRegistration();
    }

    public void handleReviewRace() {
        // TODO
        view.viewReviewRace(model.reviewRace());
    }

    public void handleAccount() {
        view.viewAccount(model.getUser().getUserData());
    }

    public void handleErrors() {
        view.viewErrors();
    }

    public void setUser(UserContext user) {
        model.setUser(user);
    }
}
