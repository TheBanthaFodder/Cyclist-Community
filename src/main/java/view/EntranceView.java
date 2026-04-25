package main.java.view;

public class EntranceView extends CliView {

    public EntranceView() {
        super();
    }
    
    //first user interaction
    public String entrance() {//welcome prompt
    	return promptForInput("Please select an option:\n"
    			+ "1: Login\n"
    			+ "2: Sign Up\n");
    }
    
    public String userSelection() { //for sign up
    	return promptForInput("Select User Type:\n"
    			+ "1: Administrator\n"
    			+ "2: Organizer\n"
    			+ "3: Racer\n");
    }

    public String viewLogin() { // for log in
        promptForInput("Please enter your unique Id:");
    }

    public void viewSignUp(String id) {
        printMessage("You have signed up!\n"
        		+ "Your Unique ID is " + id + "\n"
        		+"Please use this to login\n");
    }

    @Override
    public String getUserInput() { 
        return super.getUserInput("Entrance input: ");
    }
}