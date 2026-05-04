package model.user;

/**
 * Racer Class
 * 
 * This class represents a Racer in the system.
 * It implements BOTH:
 *  - UserStrategy (Strategy Pattern)
 *  - Observer (Observer Pattern)
 * 
 * This allows a racer to:
 * 1. Act as a strategy for retrieving user data
 * 2. Receive updates when their category changes
 */

import java.util.ArrayList;
import java.util.List;
import model.observer.Observer;
import model.race.Race;

public class Racer extends User implements Observer {

    private final String ccInfo;   // Credit card info (simplified)
    private int cat;               // Current category (5 -> 1)
    private int podiums;           // Number of podium finishes
    private final List<Race> racesAttended;
    private int totalWins;


   /**
     * Constructor for Racer
     */

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


    /**
     * Strategy Pattern Method
     * 
     * Returns formatted user data for this racer.
     */

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


    /**
     * Observer Pattern Method
     * 
     * This method is called when the Subject (Category)
     * notifies observers of an upgrade.
     */

    @Override
    public void receiveCategoryUpdate() {
       // Only upgrade if not already at highest category
	 if (cat > 1) {
            cat--;

	    // Reset podium count after upgrade
            podiums = podiums - 5;
        }
    }
}
