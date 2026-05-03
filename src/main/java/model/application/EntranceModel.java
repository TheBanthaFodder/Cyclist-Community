package model.application;

import java.util.HashMap; 
import java.util.Map;

import model.user.Administrator;
import model.user.Organizer;
import model.user.Racer;
import model.user.UserContext;

public class EntranceModel {

    private UserContext userType;
    
    //maps to store user types for login with unique ID
    Map<String, UserContext> Racers = new HashMap<>();
    Map<String, UserContext> Organizers = new HashMap<>();
    Map<String, UserContext> Admin = new HashMap<>();
     
    //check to see if 
    public UserContext logIn(String id) {
    	if(Racers.containsKey(id)) {
    		return Racers.get(id);
    	}
    	if(Admin.containsKey(id)) {
    		return Admin.get(id);
    	}
    	if(Organizers.containsKey(id)) {
    		return Organizers.get(id);
    	}
    	return null;
    }

    public boolean validateUserAccount() {
        return !Racers.isEmpty() || !Admin.isEmpty() || !Organizers.isEmpty();
    }
    
   
    public String signUpRacer(String name, String ccInfo) {
    	UserContext copy = new UserContext();
        copy.setUserStrategy(new Racer(ccInfo, name)); //set type and return unique ID
        
        int num = Racers.size();
        num++;
        String uniqueID = "Racer" + num;
        
        Racers.put(uniqueID,copy);
        userType = copy;
        
        return uniqueID;
    }
    
    public String signUpOrganizer(String name) {
    	UserContext copy = new UserContext();
        copy.setUserStrategy(new Organizer(name)); //set type and return unique ID
        int num = Organizers.size();
        num++;
        String uniqueID = "Organizer" + num;
        
        Organizers.put(uniqueID,copy);
        userType = copy;
        return uniqueID;
    }

    public String signUpAdministrator(String name) {
    	UserContext copy = new UserContext();
        copy.setUserStrategy(new Administrator(name)); //set type and return unique ID
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
