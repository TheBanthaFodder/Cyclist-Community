package model.application;

import java.util.List;

import model.race.Race;
import model.race.RaceLicense;
import model.race.RaceRegistration;
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
        // TODO
        return OrganizerModel.getAllRaces();
    }

    public List<Race> reviewRace() {
        // TODO
        return raceResult.reviewRace();
    }

    public boolean registerForRace(int raceIndex) {
        // TODO
        return false;
    }

    public int getPlacementForRace(int raceIndex) {
        // TODO
        return 0;
    }

    public void addFeedback(int raceIndex, String feedback) {
        // TODO
    }

    public String getFeedbackForRace(int raceIndex) {
        // TODO
        return "";
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
