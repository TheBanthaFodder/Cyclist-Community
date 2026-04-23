package model.race;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import model.user.Racer;

public class RaceResult {

    private static final List<Race> pastRaces = new ArrayList<>();
    private static final Map<String, Integer> placementsByRacer = new LinkedHashMap<>();
    private static final Map<String, String> feedbackByRacer = new LinkedHashMap<>();
    private static Race latestRace;

    public void addResults() {
        // TODO
        if (!pastRaces.contains(latestRace)) {
            pastRaces.add(latestRace);
        }
    }

    public void addResults(Race race) {
        latestRace = race;
        addResults();
    }

    public void recordResult(Race race, Racer racer, int placement) {
        // TODO
    }

    public List<Race> reviewRace() {
        // TODO
        return pastRaces;
    }

    public Race getLatestRace() {
        return latestRace;
    }

    public int getPlacement(Race race, Racer racer) {
        // TODO
        return 0;
    }

    public void giveFeedback(Race race, Racer racer, String feedback) {
        // TODO
    }

    public String getFeedbackForRace(Race race, Racer racer) {
        // TODO
        return "";
    }

    private String raceKey(Race race) {
        return race.getRaceType() + "-" + race.getDate();
    }

    private String resultKey(Race race, Racer racer) {
        return raceKey(race) + "-" + racer.getName();
    }

    private String feedbackKey(Race race, Racer racer) {
        return raceKey(race) + "-feedback-" + racer.getName();
    }
}
