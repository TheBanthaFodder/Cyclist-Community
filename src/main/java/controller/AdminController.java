package controller;

import model.application.AdminModel;
import model.race.RaceLicense;
import model.user.UserContext;
import view.AdminView;

public class AdminController {

    private final AdminModel model;
    private final AdminView view;

    public AdminController() {
        this(new AdminModel(), new AdminView());
    }

    public AdminController(AdminModel model, AdminView view) {
        this.model = model;
        this.view = view;
    }

    public void handleUserAccounts() {
        model.userAccounts();
        view.viewUserAccounts();
    }

    public void handleManageLicenses() {
        RaceLicense license = model.raceLicenses();
        if (license == null) {
            view.addError("Select a racer account before managing licenses.");
            handleErrors();
            return;
        }
        view.viewManageLicenses();
    }

    public void handleSystemSettings() {
        model.manageSystemSettings();
        view.viewSystemSettings();
    }

    public void handleAccount() {
        view.viewAccount(model.getUser().getUserData());
    }

    public void handleErrors() {
        view.viewErrors();
    }

    public String handleMenu() {
        view.viewMenu();
        return view.getUserInput("Choose admin option: ");
    }

    public void setUser(UserContext user) {
        model.setUser(user);
    }

    public void setManagedUser(UserContext user) {
        model.setManagedUser(user);
    }
}
