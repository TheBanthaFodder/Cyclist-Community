package model.application;

import java.util.LinkedHashMap;
import java.util.Map;

import model.race.RaceLicense;
import model.user.Organizer;
import model.user.Racer;
import model.user.User;
import model.user.UserContext;

public class AdminModel {

    private UserContext user;
    private UserContext manageUser;
    private final Map<String, UserContext> racerMap;
    private final Map<String, UserContext> organizerMap;
    private final Map<String, RaceLicense> raceLicenseMap;
    private final Map<String, String> systemSettings;

    public AdminModel() {
        this.racerMap = new LinkedHashMap<>();
        this.organizerMap = new LinkedHashMap<>();
        this.raceLicenseMap = new LinkedHashMap<>();
        this.systemSettings = new LinkedHashMap<>();
        this.systemSettings.put("registrationWindowDays", "7");
        this.systemSettings.put("defaultRegistrationLimit", "50");
        this.systemSettings.put("resultsFeedbackEnabled", "true");
    }

    public Map<String, UserContext> userAccounts() {
        Map<String, UserContext> accounts = new LinkedHashMap<>();
        accounts.putAll(organizerMap);
        accounts.putAll(racerMap);
        return accounts;
    }

    public RaceLicense raceLicenses() {
        String userKey = getUserKey(manageUser);
        Racer racer = (Racer) manageUser.getUserStrategy();
        RaceLicense license = raceLicenseMap.get(userKey);
        if (license == null) {
            license = new RaceLicense(racer.getCategory());
            raceLicenseMap.put(userKey, license);
        } else {
            license.manageLicenses(racer.getCategory());
        }
        return license;
    }

    public Map<String, String> manageSystemSettings() {
        return systemSettings;
    }

    public void setUser(UserContext userType) {
        user = userType;
    }

    public void setManagedUser(UserContext userType) {
        manageUser = userType;
        registerUser(userType);
    }

    public UserContext getUser() {
        return user;
    }

    public UserContext getManagedUser() {
        return manageUser;
    }

    public Map<String, UserContext> getRacerMap() {
        return racerMap;
    }

    public Map<String, UserContext> getOrganizerMap() {
        return organizerMap;
    }

    private void registerUser(UserContext userType) {
        String userKey = getUserKey(userType);
        if (userType.getUserStrategy() instanceof Racer) {
            racerMap.put(userKey, userType);
        } else if (userType.getUserStrategy() instanceof Organizer) {
            organizerMap.put(userKey, userType);
        }
    }

    private String getUserKey(UserContext userType) {
        return ((User) userType.getUserStrategy()).getName();
    }
}
