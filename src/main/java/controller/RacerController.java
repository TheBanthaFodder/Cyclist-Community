package controller;

import java.util.List;

import model.application.RacerModel;
import model.race.Race;
import model.race.RaceRegistration.RegistrationStatus;
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
        boolean selectingRace = true;

        // Keep returning to race selection when the selected race is full or ineligible.
        while (selectingRace) {
            List<Race> races = model.getAvailableRaces();
            if (races.isEmpty()) {
                view.addError("No races have been created yet.");
                handleErrors();
                return;
            }

            view.viewAvailableRaces(races);
            int raceIndex = getRaceIndex(races.size());
            if (raceIndex < 0) {
                return;
            }

            // Checks to make sure user can actually register for race
            if (!model.raceHasSeats(raceIndex)) {
                view.viewRaceFull();
                selectingRace = wantsToSelectNewRace();
                continue;
            }

            if (!model.isEligibleForRace(raceIndex)) {
                view.viewIneligible();
                selectingRace = wantsToSelectNewRace();
                continue;
            }

            Race selectedRace = races.get(raceIndex);
            view.viewRegistrationConfirmation(selectedRace);
            if (!view.getUserInput("Confirm registration? (y/n): ").equalsIgnoreCase("y")) {
                view.viewRegistrationCancelled();
                return;
            }

            if (collectPaymentAndRegister(raceIndex)) {
                return;
            }

            selectingRace = wantsToSelectNewRace();
        }
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

    public String handleMenu() {
        view.viewMenu();
        return view.getUserInput("Choose racer option: ");
    }

    public void setUser(UserContext user) {
        model.setUser(user);
    }

    private int getRaceIndex(int raceCount) {
        try {
            int raceIndex = Integer.parseInt(view.getUserInput("Choose race number: ")) - 1;
            if (raceIndex < 0 || raceIndex >= raceCount) {
                view.addError("Choose a race from the list.");
                handleErrors();
                return -1;
            }
            return raceIndex;
        } catch (NumberFormatException error) {
            view.addError("Race number must be numeric.");
            handleErrors();
            return -1;
        }
    }

    private boolean collectPaymentAndRegister(int raceIndex) {
        boolean retryPayment = true;

        // Payment can fail without changing the selected race, so retry happens here.
        while (retryPayment) {
            String paymentInfo = view.getUserInput("Enter payment info: ");
            RegistrationStatus status = model.registerForRace(raceIndex, paymentInfo);

            switch (status) {
                case SUCCESS:
                    view.viewConfirmationSent();
                    view.viewRaceRegistration();
                    return true;
                case PAYMENT_FAILED:
                    view.viewPaymentFailure();
                    retryPayment = view.getUserInput("Retry payment? (y/n): ").equalsIgnoreCase("y");
                    break;
                case RACE_FULL:
                    view.viewRaceFull();
                    return false;
                case INELIGIBLE:
                    view.viewIneligible();
                    return false;
                default:
                    view.addError("Registration failed.");
                    handleErrors();
                    return false;
            }
        }

        view.viewRegistrationCancelled();
        return true;
    }

    private boolean wantsToSelectNewRace() {
        return view.getUserInput("Select a different race? (y/n): ").equalsIgnoreCase("y");
    }
}
