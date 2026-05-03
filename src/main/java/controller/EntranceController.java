package controller;

import model.application.EntranceModel;
import model.user.Administrator;
import model.user.Organizer;
import model.user.UserContext;
import view.EntranceView;

public class EntranceController {

    private final EntranceModel model;
    private final EntranceView view;
    private final RacerController racerController;
    private final OrganizerController organizerController;
    private final AdminController adminController;

    public EntranceController() {
        this(
            new EntranceModel(),
            new EntranceView(),
            new RacerController(),
            new OrganizerController(),
            new AdminController()
        );
    }

    public EntranceController(
        EntranceModel model,
        EntranceView view,
        RacerController racerController,
        OrganizerController organizerController,
        AdminController adminController
    ) {
        this.model = model;
        this.view = view;
        this.racerController = racerController;
        this.organizerController = organizerController;
        this.adminController = adminController;

        organizerController.setUser(new UserContext(new Organizer("Default Organizer")));
        adminController.setUser(new UserContext(new Administrator("Default Admin")));
    }

    public void runApp() {
        boolean running = true;

        while (running) {
            String choice = view.entrance();

            switch (choice) {
                case "1":
                    handleLogin();
                    break;
                case "2":
                    handleSignUp();
                    break;
                case "0":
                    running = false;
                    break;
                default:
                    view.viewMessage("Choose a valid option.");
            }
        }

        view.viewMessage("Exiting Bike Racing System.");
    }

    public void handleLogin() {
        if (!model.validateUserAccount()) {
            view.addError("No valid user account is available.");
            handleErrors();
            return;
        }

        view.viewLogin();
        String id = view.getUserInput("Enter login ID: ");
        UserContext userType = model.logIn(id);

        if (userType == null) {
            view.addError("Invalid login ID.");
            handleErrors();
            return;
        }

        sendUserType(userType);
    }

    public void handleSignUp() {
        String choice = view.userSelection();
        UserContext userType = null;
        String uniqueID = "";

        switch (choice) {
            case "1":
                String adminName = view.getUserInput("Enter administrator name: ");
                uniqueID = model.signUpAdministrator(adminName);
                userType = model.getUserType();
                adminController.setUser(userType);
                break;
            case "2":
                String organizerName = view.getUserInput("Enter organizer name: ");
                uniqueID = model.signUpOrganizer(organizerName);
                userType = model.getUserType();
                organizerController.setUser(userType);
                break;
            case "3":
                String racerName = view.getUserInput("Enter racer name: ");
                String ccInfo = view.getUserInput("Enter credit card info: ");
                uniqueID = model.signUpRacer(racerName, ccInfo);
                userType = model.getUserType();
                wireSignedUpRacer(userType);
                break;
            default:
                view.viewMessage("Choose a valid option.");
                return;
        }

        view.viewSignUp(uniqueID);
        sendUserType(userType);
    }

    public UserContext handleUser() {
        return sendUserType();
    }

    public UserContext sendUserType() {
        return model.getUserType();
    }

    public void sendUserType(UserContext userType) {
        if (userType == null) {
            return;
        }

        if (userType.getUserStrategy() instanceof Administrator) {
            adminController.setUser(userType);
            runAdministratorFlow();
        } else if (userType.getUserStrategy() instanceof Organizer) {
            organizerController.setUser(userType);
            runOrganizerFlow();
        } else {
            wireSignedUpRacer(userType);
            runRacerFlow();
        }
    }

    public void handleErrors() {
        view.viewErrors();
    }

    public void setUserType(UserContext userType) {
        model.setUserType(userType);
    }

    private void wireSignedUpRacer(UserContext racerUser) {
        racerController.setUser(racerUser);
        adminController.setManagedUser(racerUser);
    }

    private void runRacerFlow() {
        boolean inRacerMenu = true;

        while (inRacerMenu) {
            String choice = racerController.handleMenu();

            switch (choice) {
                case "1":
                    racerController.handleBuyLicense();
                    break;
                case "2":
                    racerController.handleRaceRegistration();
                    break;
                case "3":
                    racerController.handleReviewRace();
                    break;
                case "4":
                    racerController.handleAccount();
                    break;
                case "0":
                    inRacerMenu = false;
                    break;
                default:
                    view.viewMessage("Choose a valid option.");
            }
        }
    }

    private void runOrganizerFlow() {
        boolean inOrganizerMenu = true;

        while (inOrganizerMenu) {
            String choice = organizerController.handleMenu();

            switch (choice) {
                case "1":
                    organizerController.handleCreateRace();
                    break;
                case "2":
                    organizerController.handleManageRace();
                    break;
                case "3":
                    organizerController.handleAddRaceResults();
                    break;
                case "4":
                    organizerController.handleAccount();
                    break;
                case "0":
                    inOrganizerMenu = false;
                    break;
                default:
                    view.viewMessage("Choose a valid option.");
            }
        }
    }

    private void runAdministratorFlow() {
        boolean inAdminMenu = true;

        while (inAdminMenu) {
            String choice = adminController.handleMenu();

            switch (choice) {
                case "1":
                    adminController.handleUserAccounts();
                    break;
                case "2":
                    adminController.handleManageLicenses();
                    break;
                case "3":
                    adminController.handleSystemSettings();
                    break;
                case "4":
                    adminController.handleAccount();
                    break;
                case "0":
                    inAdminMenu = false;
                    break;
                default:
                    view.viewMessage("Choose a valid option.");
            }
        }
    }
}
