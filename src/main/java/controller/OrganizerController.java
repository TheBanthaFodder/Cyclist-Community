package controller;

import java.time.LocalDate;
import java.util.List;

import model.application.OrganizerModel;
import model.race.Race;
import model.user.UserContext;
import view.OrganizerView;

public class OrganizerController {

    private final OrganizerModel model;
    private final OrganizerView view;

    public OrganizerController() {
        this(new OrganizerModel(), new OrganizerView());
    }

    public OrganizerController(OrganizerModel model, OrganizerView view) {
        this.model = model;
        this.view = view;
    }

    public void handleCreateRace() {
        String raceType = view.getUserInput("Enter race type: ");
        LocalDate date = LocalDate.parse(view.getUserInput("Enter race date (YYYY-MM-DD): "));
        double miles = Double.parseDouble(view.getUserInput("Enter race miles: "));
        String directions = view.getUserInput("Enter route directions: ");
        int registrationLimit = Integer.parseInt(view.getUserInput("Enter participant limit: "));
        LocalDate lastDay = LocalDate.parse(
            view.getUserInput("Enter last day to register (YYYY-MM-DD): ")
        );
        boolean official = view.getUserInput("Is this race official? (y/n): ").equalsIgnoreCase("y");

        Race race = model.createRace(
            date,
            raceType,
            registrationLimit,
            official,
            lastDay,
            miles,
            directions
        );
        view.viewCreateRace(race);
    }

    public void handleManageRace() {
        view.viewManageRace(model.manageRace());
    }

    public void handleAddRaceResults() {
        // TODO
        view.viewAddRaceResults();
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
