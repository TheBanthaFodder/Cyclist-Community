package src.main.java.controller;

import src.main.java.model.application.EntranceModel; 
import src.main.java.model.user.UserContext;
import src.main.java.view.EntranceView;
import src.main.java.controller.*;

public class EntranceController {

    private final EntranceModel model;
    private final EntranceView view;

    public EntranceController() {
        this(new EntranceModel(), new EntranceView());
    }

    public EntranceController(EntranceModel model, EntranceView view) {
        this.model = model;
        this.view = view;
    }
    
    public void entranceLoop() {
    	boolean signUp = false;
    	while(true) {
    		String step1 = view.entrance(); //login or sign up?
    		try {
    			int choice = Integer.parseInt(step1); //1:login or 2:sign up
    			
    			switch(choice) {
    			case 1: //login
    				//collectId
    				String uniqueId = view.viewLogin();
    				sendUserType(handleLogin(uniqueId), uniqueId);//Send to user controller
    				break;
    				
    			case 2: //sign up
    				
    				choice = Integer.parseInt(view.userSelection());//user type?
    				
    				switch(choice) { //handle login based on user type.
    				case 1:
    					handleSignUp("Administrator");
    					break;
    				case 2:
    					handleSignUp("Organizer");
    					break;
    				case 3:
    					handleSignUp("Racer");
    					break;
    				}
    				break;
    			}
    			
    			if(signUp == true) {
    				continue;//redirect to login
    			}
    			
    			break; //break out of loop 
    		}
    		catch(NumberFormatException e){
    			view.addError("Please select numbers 1, 2 or 3(if applicable)" + e.getMessage());
    			view.viewErrors();
    			continue;
    		}
    	}
    }

    public UserContext handleLogin(String userType) { 
    	String UniqueId = null;
    	UserContext UserAccount = model.logIn(UniqueId);
    	if(UserAccount == null) view.addError(UniqueId);
    }

    public void handleSignUp(String userType) {
    	String UniqueId = null;
    	switch(userType) {
    	case "Administrator":
    		String AdminName = view.getUserInput("Enter name: ");
    		model.signUpAdministrator(AdminName);
    		break;
    	case "Organizer":
    		String OrganizerName = view.getUserInput("Enter name: ");
    		model.signUpOrganizer(OrganizerName);
    		break;
    	case "Racer":
    		String RacerName = view.getUserInput("Enter racer name: ");
    		String ccInfo = view.getUserInput("Enter credit card info: ");
    		model.signUpRacer(RacerName, ccInfo);
    		break;
    	}
        view.viewSignUp(UniqueId);
    }

    public UserContext handleUser() {
        return sendUserType();
    }

    //sends userContext aka user Account to User Controller
    public void sendUserType(UserContext userAccount, String id) {
        char key = id.charAt(0);
        
        switch(key) {
        case 'A':
        	AdminController Auser;
        	Auser.setUser(userAccount);
        	//TODO call user loop
        	break;
        case 'O':
        	OrganizerController Ouser;
        	Ouser.setUser(userAccount);
        	//TODO call user loop
        	break;
        case 'R':
        	RacerController Ruser;
        	Ruser.setUser(userAccount);
        	//TODO call user loop
        	break;
        }
    }

    public void handleErrors() {
        view.viewErrors();
    }

}
