package model.race;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.user.Racer;

public class Race {

    private LocalDate date;
    private String raceType;
    private int registrationLimit;
    private boolean official;
    private LocalDate lastDayToRegister;
    private final List<Racer> participants;
    private final List<Category> categories;
    private Route route;

    public Race() {
        this(
            LocalDate.now().plusWeeks(2),
            "Road Race",
            50,
            true,
            LocalDate.now().plusWeeks(1),
            new Route(20.0, "Main route")
        );
    }

    public Race(
        LocalDate date,
        String raceType,
        int registrationLimit,
        boolean official,
        LocalDate lastDayToRegister,
        Route route
    ) {
        this.participants = new ArrayList<>();
        this.categories = Category.getAllCategories();
        createRace(date, raceType, registrationLimit, official, lastDayToRegister, route);
    }

    public void createRace(
        LocalDate date,
        String raceType,
        int registrationLimit,
        boolean official,
        LocalDate lastDayToRegister,
        Route route
    ) {
        this.date = date;
        this.raceType = raceType;
        this.registrationLimit = registrationLimit;
        this.official = official;
        this.lastDayToRegister = lastDayToRegister;
        this.route = route;
    }

    public void setRegistrationLimit() {
        if (registrationLimit < participants.size()) {
            registrationLimit = participants.size();
        }
    }

    public void setRegistrationLimit(int registrationLimit) {
        this.registrationLimit = registrationLimit;
    }

    public boolean registerParticipant(Racer racer) {
        if (participants.size() >= registrationLimit || !isRegistrationOpen()) {
            return false;
        }

        participants.add(racer);
        racer.addRaceAttended(this);
        return true;
    }

    public boolean isRegistrationOpen() {
        return !LocalDate.now().isAfter(lastDayToRegister);
    }

    public LocalDate getDate() {
        return date;
    }

    public String getRaceType() {
        return raceType;
    }

    public int getRegistrationLimit() {
        return registrationLimit;
    }

    public boolean isOfficial() {
        return official;
    }

    public LocalDate getLastDayToRegister() {
        return lastDayToRegister;
    }

    public List<Racer> getParticipants() {
        return participants;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    @Override
    public String toString() {
        return raceType + " on " + date + " (" + (official ? "Official" : "Unofficial") + ")";
    }
}
