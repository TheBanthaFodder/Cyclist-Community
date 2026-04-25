package main.java.view;

public class AdminView extends CliView {

    public AdminView() {
        super();
    }

    public void viewUserAccounts() {
        printMessage("Displaying user accounts.");
    }

    public void viewManageLicenses() {
        printMessage("Displaying license management.");
    }

    public void viewSystemSettings() {
        printMessage("Displaying system settings.");
    }

    @Override
    public String getUserInput() {
        return super.getUserInput("Admin input: ");
    }

    public void viewAccount(String accountData) {
        printMessage("Displaying administrator account: " + accountData);
    }
}
