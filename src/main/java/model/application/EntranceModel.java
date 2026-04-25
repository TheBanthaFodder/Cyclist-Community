package model.application;

<<<<<<< HEAD
import java.util.HashMap; 
import java.util.Map;

import main.java.model.user.Administrator;
import main.java.model.user.Organizer;
import main.java.model.user.Racer;
import src.main.java.model.user.UserContext;
=======
import model.race.Category;
import model.user.Racer;
import model.user.UserContext;
>>>>>>> 342d261c637a9ba762e255b2b22630c208b424c4

public class EntranceModel {

    
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
    
   
    public String signUpRacer(String name, String ccInfo) {
    	UserContext copy;
        copy.setUserStrategy(new Racer(ccInfo, name)); //set type and return unique ID
        
        int num = Racers.size();
        num++;
        String uniqueID = "Racer" + num;
        
        Racers.put(uniqueID,copy);
        
        return uniqueID;
    }
    
    public String signUpOrganizer(String name) {
    	UserContext copy;
        copy.setUserStrategy(new Organizer(name)); //set type and return unique ID
        int num = Organizers.size();
        num++;
        String uniqueID = "Organizer" + num; //TODO add all contexts to maps
        
        Organizers.put(uniqueID,copy);
        return uniqueID;
    }

<<<<<<< HEAD
    public String signUpAdministrator(String name) {
    	UserContext copy;
        copy.setUserStrategy(new Administrator(name)); //set type and return unique ID
        int num = Admin.size();
        num++;
        String uniqueID = "Admin" + num;
        
        Admin.put(uniqueID,copy);
        return uniqueID;
=======
    public UserContext signUp(String name, String ccInfo) {
        Racer racer = new Racer(ccInfo, name);
        Category.attachRacer(racer);
        userType = new UserContext(racer);
        return userType;
    }

    public void setUserType(UserContext userType) {
        this.userType = userType;
    }

    public UserContext getUserType() {
        return userType;
>>>>>>> 342d261c637a9ba762e255b2b22630c208b424c4
    }
    
    
}
