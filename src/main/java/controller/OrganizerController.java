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
        List<Race> races = model.getManagedRaces();
        if (races.isEmpty()) {
            view.addError("No races are available.");
            handleErrors();
            return;
        }

        view.viewManageRace(races);
        int raceIndex = Integer.parseInt(view.getUserInput("Choose race number: ")) - 1;
        Race race = races.get(raceIndex);

        if (race.getParticipants().isEmpty()) {
            view.addError("No racers are registered for this race.");
            handleErrors();
            return;
        }

        view.viewParticipants(race);
        int racerIndex = Integer.parseInt(view.getUserInput("Choose racer number: ")) - 1;
        int placement = Integer.parseInt(view.getUserInput("Enter racer placement: "));
        model.addRaceResult(raceIndex, racerIndex, placement);
        view.viewAddRaceResults();
    }

    public void handleAccount() {
        view.viewAccount(model.getUser().getUserData());
    }

    public void handleErrors() {
        view.viewErrors();
    }

    public String handleMenu() {
        view.viewMenu();
        return view.getUserInput("Choose organizer option: ");
    }

    public void setUser(UserContext user) {
        model.setUser(user);
    }
}
