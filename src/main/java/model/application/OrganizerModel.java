package model.application;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.race.Category;
import model.race.Race;
import model.race.RaceResult;
import model.race.Route;
import model.user.Racer;
import model.user.UserContext;

public class OrganizerModel {

    private static final List<Race> managedRaces = new ArrayList<>();

    private UserContext user;
    private final RaceResult raceResult;

    public OrganizerModel() {
        this.raceResult = new RaceResult();
    }

    public Race createRace(
        LocalDate date,
        String raceType,
        int registrationLimit,
        boolean official,
        LocalDate lastDayToRegister,
        double miles,
        String directions
    ) {
        Route route = new Route(miles, directions);
        Race race = new Race(date, raceType, registrationLimit, official, lastDayToRegister, route);
        managedRaces.add(race);
        return race;
    }

    public List<Race> manageRace() {
        for (Race race : managedRaces) {
            race.setRegistrationLimit();
        }
        return managedRaces;
    }

    public List<Race> addRaceResults() {
        return raceResult.reviewRace();
    }

    public void addRaceResult(int raceIndex, int racerIndex, int placement) {
        Race race = managedRaces.get(raceIndex);
        Racer racer = race.getParticipants().get(racerIndex);

        raceResult.recordResult(race, racer, placement);
        racer.recordRaceResult(race, placement, race.isOfficial());

        for (Category category : race.getCategories()) {
            category.checkForUpgrade();
        }
    }

    public void setUser(UserContext userType) {
        user = userType;
    }

    public UserContext getUser() {
        return user;
    }

    public List<Race> getManagedRaces() {
        return managedRaces;
    }

    public static List<Race> getAllRaces() {
        return managedRaces;
    }
}
