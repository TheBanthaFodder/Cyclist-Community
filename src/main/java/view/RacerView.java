package main.java.view;

import java.util.List; 

import main.java.model.race.Race;

public class RacerView extends CliView {

    public RacerView() {
        super();
    }

    public void viewSystemSignUp() {
        printMessage("Racer account created.");
    }

    public void viewBuyLicense() {
        printMessage("Race license purchased.");
    }

    public void viewAvailableRaces(List<Race> races) {
        printMessage("Available races:");
        for (int i = 0; i < races.size(); i++) {
            printMessage((i + 1) + ". " + races.get(i));
        }
    }

    public void viewRaceRegistration() {
        printMessage("Race registration complete.");
    }

    public void viewReviewRace(List<Race> races) {
        printMessage("Past races:");
        for (int i = 0; i < races.size(); i++) {
            printMessage((i + 1) + ". " + races.get(i));
        }
    }

    public void viewSelectedRace(Race race, int placement, String feedback) {
        printMessage("Reviewing: " + race);
        printMessage("Recorded placement: " + placement);
        printMessage("Current feedback: " + feedback);
    }

    public void viewFeedbackSaved() {
        printMessage("Feedback saved.");
    }

    @Override
    public String getUserInput() {
        return super.getUserInput("Racer input: ");
    }

    public void viewAccount(String accountData) {
        printMessage("Displaying racer account: " + accountData);
    }
}
