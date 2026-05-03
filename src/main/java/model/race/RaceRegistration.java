package model.race;

import model.user.Racer;

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
        Category.attachRacer(racer);
        return racer;
    }

    public Racer newRacer(String ccInfo, String name) {
        racer = new Racer(ccInfo, name);
        Category.attachRacer(racer);
        return racer;
    }

    public RaceLicense purchaseLicense() {
        // Provides option of upgrading to new category of license
        if (license == null) {
            license = new RaceLicense(racer.getCategory());
        } else {
            license.manageLicenses(racer.getCategory());
        }
        return license;
    }

    public boolean signUpForRace() {
        if (race.isOfficial()) {
            if (license == null || !license.isActive()) {
                return false;
            }

            // racer may be correct cat but haven't updated license
            if (license.getCategoryLevel() != racer.getCategory()) {
                return false;
            }
        }

        return race.registerParticipant(racer);
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
