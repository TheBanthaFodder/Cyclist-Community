package main.java.model.race;

import main.java.model.user.Racer;

public class RaceRegistration {

    private Racer racer;
    private RaceLicense license;
    private Race race;

    public RaceRegistration() {
    }

    public RaceRegistration(Racer racer) {
        this.racer = racer;
    }

    public Racer newRacer() {
        racer = new Racer("CC-NEW", "New Racer");
        return racer;
    }

    public Racer newRacer(String ccInfo, String name) {
        racer = new Racer(ccInfo, name);
        return racer;
    }

    public RaceLicense purchaseLicense() {
        // TODO
        if (license == null) {
            license = new RaceLicense();
        }
        return license;
    }

    public boolean signUpForRace() {
        // TODO
        return false;
    }

    public boolean signUpForRace(Race selectedRace) {
        race = selectedRace;
        return signUpForRace();
    }

    public Racer getRacer() {
        return racer;
    }

    public void setRacer(Racer racer) {
        this.racer = racer;
    }

    public RaceLicense getLicense() {
        return license;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }
}
