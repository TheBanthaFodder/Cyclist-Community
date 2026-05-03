package model.application;

import java.util.ArrayList;
import java.util.List;

import model.race.Race;
import model.race.RaceLicense;
import model.race.RaceRegistration;
import model.race.RaceRegistration.RegistrationStatus;
import model.race.RaceResult;
import model.user.Racer;
import model.user.UserContext;

public class RacerModel {

    private UserContext user;
    private final RaceRegistration raceRegistration;
    private final RaceResult raceResult;

    public RacerModel() {
        this.raceRegistration = new RaceRegistration();
        this.raceResult = new RaceResult();
    }

    public RaceRegistration raceRegistration() {
        return raceRegistration;
    }

    public UserContext signUp(String name, String ccInfo) {
        Racer racer = raceRegistration.newRacer(ccInfo, name);
        user = new UserContext(racer);
        raceRegistration.setRacer(racer);
        return user;
    }

    public RaceLicense buyLicense() {
        return raceRegistration.purchaseLicense();
    }

    public List<Race> getAvailableRaces() {
        return OrganizerModel.getAllRaces();
    }

    public List<Race> reviewRace() {
        List<Race> reviewableRaces = new ArrayList<>();
        List<Race> allResults = raceResult.reviewRace();
        List<Race> racerRaces = getCurrentRacer().getRacesAttended();

        for (Race race : allResults) {
            if (racerRaces.contains(race)) {
                reviewableRaces.add(race);
            }
        }

        return reviewableRaces;
    }

    public boolean registerForRace(int raceIndex) {
        Race race = getAvailableRaces().get(raceIndex);
        raceRegistration.setRace(race);
        return raceRegistration.signUpForRace();
    }

    public RegistrationStatus registerForRace(int raceIndex, String paymentInfo) {
        Race race = getAvailableRaces().get(raceIndex);
        raceRegistration.setRace(race);
        return raceRegistration.registerWithPayment(paymentInfo);
    }

    public boolean raceHasSeats(int raceIndex) {
        return getAvailableRaces().get(raceIndex).hasAvailableSeats();
    }

    public boolean isEligibleForRace(int raceIndex) {
        Race race = getAvailableRaces().get(raceIndex);
        // Eligibility depends on both the selected race and any license this racer has bought.
        return race.isEligible(getCurrentRacer(), raceRegistration.getLicense());
    }

    public int getPlacementForRace(int raceIndex) {
        Race race = reviewRace().get(raceIndex);
        return raceResult.getPlacement(race, getCurrentRacer());
    }

    public void addFeedback(int raceIndex, String feedback) {
        Race race = reviewRace().get(raceIndex);
        raceResult.giveFeedback(race, getCurrentRacer(), feedback);
    }

    public String getFeedbackForRace(int raceIndex) {
        Race race = reviewRace().get(raceIndex);
        return raceResult.getFeedbackForRace(race, getCurrentRacer());
    }

    public void setUser(UserContext userType) {
        user = userType;
        raceRegistration.setRacer((Racer) userType.getUserStrategy());
    }

    public UserContext getUser() {
        return user;
    }

    private Racer getCurrentRacer() {
        return (Racer) user.getUserStrategy();
    }
}
