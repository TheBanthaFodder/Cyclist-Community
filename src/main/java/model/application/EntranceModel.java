package model.application;

import java.util.HashMap; 
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.user.Administrator;
import model.user.Organizer;
import model.user.Racer;
import model.user.UserContext;

public class EntranceModel {

    private UserContext userType;
    
    // Each map stores accounts for one role. The generated ID is the login key.
    Map<String, UserContext> Racers = new HashMap<>();
    Map<String, UserContext> Organizers = new HashMap<>();
    Map<String, UserContext> Admin = new HashMap<>();
     
    // Search each role map for the login ID and remember the matched account as current.
    public UserContext logIn(String id) {
    	if(Racers.containsKey(id)) {
    		userType = Racers.get(id);
    		return userType;
    	}
    	if(Admin.containsKey(id)) {
    		userType = Admin.get(id);
    		return userType;
    	}
    	if(Organizers.containsKey(id)) {
    		userType = Organizers.get(id);
    		return userType;
    	}
    	return null;
    }

    public boolean validateUserAccount() {
        return !Racers.isEmpty() || !Admin.isEmpty() || !Organizers.isEmpty();
    }

    // Used by the login screen so demo users can see which IDs are available.
    public List<String> getLoginIDs() {
        List<String> loginIDs = new ArrayList<>();
        loginIDs.addAll(Racers.keySet());
        loginIDs.addAll(Organizers.keySet());
        loginIDs.addAll(Admin.keySet());
        return loginIDs;
    }
    
   
    public String signUpRacer(String name, String ccInfo) {
    	UserContext copy = new UserContext();
        copy.setUserStrategy(new Racer(ccInfo, name));
        
        // IDs stay simple for the CLI: Racer1, Racer2, and so on.
        int num = Racers.size();
        num++;
        String uniqueID = "Racer" + num;
        
        Racers.put(uniqueID,copy);
        userType = copy;
        
        return uniqueID;
    }
    
    public String signUpOrganizer(String name) {
    	UserContext copy = new UserContext();
        copy.setUserStrategy(new Organizer(name));
        // IDs stay simple for the CLI: Organizer1, Organizer2, and so on.
        int num = Organizers.size();
        num++;
        String uniqueID = "Organizer" + num;
        
        Organizers.put(uniqueID,copy);
        userType = copy;
        return uniqueID;
    }

    public String signUpAdministrator(String name) {
    	UserContext copy = new UserContext();
        copy.setUserStrategy(new Administrator(name));
        // IDs stay simple for the CLI: Admin1, Admin2, and so on.
        int num = Admin.size();
        num++;
        String uniqueID = "Admin" + num;
        
        Admin.put(uniqueID,copy);
        userType = copy;
        return uniqueID;
    }

    public void setUserType(UserContext userType) {
        this.userType = userType;
    }

    public UserContext getUserType() {
        return userType;
    }
    
    
}
