package model.user;

import java.util.ArrayList;
import java.util.List;
import model.observer.Observer;
import model.race.Race;

public class Racer extends User implements Observer {

    private final String ccInfo;
    private int cat;
    private int podiums;
    private final List<Race> racesAttended;
    private int totalWins;

    public Racer(String ccInfo, String name) {
        super(name);
        this.ccInfo = ccInfo;
        this.cat = 5;
        this.podiums = 0;
        this.racesAttended = new ArrayList<>();
        this.totalWins = 0;
    }

    public String getCcInfo() {
        return ccInfo;
    }

    public int getCategory() {
        return cat;
    }

    public int getPodiums() {
        return podiums;
    }

    public List<Race> getRacesAttended() {
        return racesAttended;
    }

    public int getTotalWins() {
        return totalWins;
    }

    public void addRaceAttended(Race race) {
        if (!racesAttended.contains(race)) {
            racesAttended.add(race);
        }
    }

    public void recordRaceResult(Race race, int placement, boolean officialRace) {
        addRaceAttended(race);

        if (placement == 1) {
            totalWins++;
        }

        if (officialRace && placement <= 3) {
            podiums++;
        }
    }

    public boolean readyForUpgrade() {
        return podiums >= 5 && cat > 1;
    }

    @Override
    public String getUserData() {
        return String.format(
            "Racer{name='%s', ccInfo='%s', category=%d, podiums=%d, totalWins=%d}",
            getName(),
            ccInfo,
            cat,
            podiums,
            totalWins
        );
    }

    @Override
    public void receiveCategoryUpdate() {
        if (cat > 1) {
            cat--;
            podiums = podiums - 5;
        }
    }
}
