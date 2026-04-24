package view;

import java.util.List;

import model.race.Race;
import model.user.Racer;

public class OrganizerView extends CliView {

    public OrganizerView() {
        super();
    }

    public void viewCreateRace(Race race) {
        printMessage("Created race: " + race);
        printMessage("Route: " + race.getRoute().getRoute());
    }

    public void viewManageRace(List<Race> races) {
        printMessage("Organizer races:");
        for (int i = 0; i < races.size(); i++) {
            printMessage((i + 1) + ". " + races.get(i));
        }
    }

    public void viewParticipants(Race race) {
        printMessage("Participants for " + race + ":");
        List<Racer> racers = race.getParticipants();
        for (int i = 0; i < racers.size(); i++) {
            printMessage((i + 1) + ". " + racers.get(i).getName());
        }
    }

    public void viewAddRaceResults() {
        printMessage("Race result added.");
    }

    @Override
    public String getUserInput() {
        return super.getUserInput("Organizer input: ");
    }

    public void viewAccount(String accountData) {
        printMessage("Displaying organizer account: " + accountData);
    }
}
