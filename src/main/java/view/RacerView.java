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
            Race race = races.get(i);
            printMessage(
                (i + 1)
                    + ". "
                    + race
                    + " - "
                    + race.getSeatsRemaining()
                    + " seats remaining"
            );
        }
    }

    public void viewRaceRegistration() {
        printMessage("Race registration complete.");
    }

    public void viewRaceFull() {
        printMessage("Selected race is full.");
    }

    public void viewIneligible() {
        printMessage("You are not eligible for this race.");
    }

    public void viewRegistrationConfirmation(Race race) {
        printMessage("Confirming registration for: " + race);
    }

    public void viewPaymentFailure() {
        printMessage("Payment was unsuccessful.");
    }

    public void viewConfirmationSent() {
        printMessage("Confirmation sent.");
    }

    public void viewRegistrationCancelled() {
        printMessage("Registration cancelled.");
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

    public void viewMenu() {
        printMessage("");
        printMessage("Racer Menu");
        printMessage("1. Buy license");
        printMessage("2. Register for race");
        printMessage("3. Review race");
        printMessage("4. View account");
        printMessage("0. Back");
    }

    @Override
    public String getUserInput() {
        return super.getUserInput("Racer input: ");
    }

    public void viewAccount(String accountData) {
        printMessage("Displaying racer account: " + accountData);
    }
}
