package view;

import java.util.List;

import model.race.Race;

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
        // TODO
        printMessage("Past races:");
        for (int i = 0; i < races.size(); i++) {
            printMessage((i + 1) + ". " + races.get(i));
        }
    }

    public void viewSelectedRace(Race race, int placement, String feedback) {
        // TODO
        printMessage("Race detail view not finished yet.");
    }

    public void viewFeedbackSaved() {
        // TODO
        printMessage("Feedback flow not finished yet.");
    }

    @Override
    public String getUserInput() {
        return super.getUserInput("Racer input: ");
    }

    public void viewAccount(String accountData) {
        printMessage("Displaying racer account: " + accountData);
    }
}
