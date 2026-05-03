package view;

public class EntranceView extends CliView {

    public EntranceView() {
        super();
    }

    public void viewLogin() {
        printMessage("Displaying login form.");
    }

    public void viewSignUp() {
        printMessage("Account created.");
    }

    public void viewSignUp(String uniqueID) {
        viewSignUp();
        printMessage("Your login ID is: " + uniqueID);
        printMessage("Use this ID to login");
    }

    public String entrance() {
        printMessage("");
        printMessage("Bike Racing System");
        printMessage("1. Login");
        printMessage("2. Sign Up");
        printMessage("0. Exit");
        return getUserInput("Choose option: ");
    }

    public String userSelection() {
        printMessage("");
        printMessage("Select User Type");
        printMessage("1. Administrator");
        printMessage("2. Organizer");
        printMessage("3. Racer");
        return getUserInput("Choose user type: ");
    }

    public void viewMessage(String message) {
        printMessage(message);
    }

    @Override
    public String getUserInput() {
        return super.getUserInput("Entrance input: ");
    }
}
