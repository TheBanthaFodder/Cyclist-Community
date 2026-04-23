package view;

public class EntranceView extends CliView {

    public EntranceView() {
        super();
    }

    public void viewLogin() {
        printMessage("Displaying login form.");
    }

    public void viewSignUp() {
        printMessage("Displaying sign-up form.");
    }

    @Override
    public String getUserInput() {
        return super.getUserInput("Entrance input: ");
    }
}
