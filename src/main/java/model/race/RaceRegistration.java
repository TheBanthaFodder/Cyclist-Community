package model.race;

import model.user.Racer;

public class RaceRegistration {

    public enum RegistrationStatus {
        SUCCESS,
        RACE_FULL,
        INELIGIBLE,
        PAYMENT_FAILED
    }

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
        return registerWithPayment(racer.getCcInfo()) == RegistrationStatus.SUCCESS;
    }

    public RegistrationStatus registerWithPayment(String paymentInfo) {
        // report the first reason registration cannot continue.
        if (!race.hasAvailableSeats()) {
            return RegistrationStatus.RACE_FULL;
        }

        if (!race.isEligible(racer, license)) {
            return RegistrationStatus.INELIGIBLE;
        }

        if (!processPayment(paymentInfo)) {
            return RegistrationStatus.PAYMENT_FAILED;
        }

        if (!race.registerParticipant(racer)) {
            return RegistrationStatus.RACE_FULL;
        }

        return RegistrationStatus.SUCCESS;
    }

    public boolean signUpForRace(Race selectedRace) {
        race = selectedRace;
        return signUpForRace();
    }

    public boolean processPayment(String paymentInfo) {
        // fake payment system for implementation
        return paymentInfo != null
            && !paymentInfo.trim().isEmpty()
            && !paymentInfo.equalsIgnoreCase("fail")
            && !paymentInfo.equalsIgnoreCase("decline");
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
