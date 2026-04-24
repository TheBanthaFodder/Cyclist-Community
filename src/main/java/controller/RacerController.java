package controller;

import java.util.List;

import model.application.RacerModel;
import model.race.Race;
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
        List<Race> races = model.getAvailableRaces();
        if (races.isEmpty()) {
            view.addError("No races have been created yet.");
            handleErrors();
            return;
        }

        view.viewAvailableRaces(races);
        int raceIndex = Integer.parseInt(view.getUserInput("Choose race number: ")) - 1;
        boolean registered = model.registerForRace(raceIndex);

        if (!registered) {
            view.addError("Registration failed.");
            handleErrors();
            return;
        }

        view.viewRaceRegistration();
    }

    public void handleReviewRace() {
        List<Race> races = model.reviewRace();
        if (races.isEmpty()) {
            view.addError("No race results are available to review yet.");
            handleErrors();
            return;
        }

        view.viewReviewRace(races);
        int raceIndex = Integer.parseInt(view.getUserInput("Choose race number to review: ")) - 1;
        int placement = model.getPlacementForRace(raceIndex);
        String existingFeedback = model.getFeedbackForRace(raceIndex);
        view.viewSelectedRace(races.get(raceIndex), placement, existingFeedback);

        String feedback = view.getUserInput("Enter feedback for this race: ");
        model.addFeedback(raceIndex, feedback);
        view.viewFeedbackSaved();
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
