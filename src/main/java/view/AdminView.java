package view;

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

    public void viewMenu() {
        printMessage("");
        printMessage("Admin Menu");
        printMessage("1. View user accounts");
        printMessage("2. Manage licenses");
        printMessage("3. View system settings");
        printMessage("4. View account");
        printMessage("0. Back");
    }

    @Override
    public String getUserInput() {
        return super.getUserInput("Admin input: ");
    }

    public void viewAccount(String accountData) {
        printMessage("Displaying administrator account: " + accountData);
    }
}
