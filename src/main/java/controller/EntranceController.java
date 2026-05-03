package controller;

import java.time.LocalDate;

import model.application.EntranceModel;
import model.application.OrganizerModel;
import model.race.Race;
import model.user.Administrator;
import model.user.Organizer;
import model.user.Racer;
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

        seedDummyData();
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

    // Gets the user's unique ID, checks if the account exists,
    // and returns the matching UserContext if login is successful.
    public void handleLogin() {
        if (!model.validateUserAccount()) {
            view.addError("No valid user account is available.");
            handleErrors();
            return;
        }

        boolean retry = true;
        while (retry) {
            view.viewLogin();
            // Login is ID-based, so show the generated demo IDs before prompting.
            view.viewLoginIDs(model.getLoginIDs());
            String id = view.getUserInput("Enter login ID: ");
            UserContext userType = model.logIn(id);

            if (userType != null) {
                sendUserType(userType);
                return;
            }

            view.addError("Invalid login ID.");
            handleErrors();
            retry = view.getUserInput("Retry login? (y/n): ").equalsIgnoreCase("y");
        }
    }

    // Creates a new user account based on the selected role.
    // The selected role becomes the user's strategy inside UserContext.
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

    // Routes the logged-in user to the correct controller based on their role.
    // This supports the Strategy pattern because each user type has different behavior.
    public UserContext sendUserType() {
        return model.getUserType();
    }

    public void sendUserType(UserContext userType) {
        if (userType == null) {
            return;
        }

        // Route the shared UserContext to the controller that matches the actual role.
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

    private void seedDummyData() {
        // Dummy data for startup so the registration activity diagram can be executed.
        // These generated IDs are Racer1, Organizer1, and Admin1.
        String racerID = model.signUpRacer("Demo Racer", "4111111111111111");
        wireSignedUpRacer(model.logIn(racerID));

        String organizerID = model.signUpOrganizer("Default Organizer");
        organizerController.setUser(model.logIn(organizerID));

        String adminID = model.signUpAdministrator("Default Admin");
        adminController.setUser(model.logIn(adminID));

        if (!OrganizerModel.getAllRaces().isEmpty()) {
            return;
        }

        // The three races cover the main registration branches: success, ineligible, and full.
        OrganizerModel seedOrganizer = new OrganizerModel();
        seedOrganizer.createRace(
            LocalDate.now().plusWeeks(2),
            "Community Fun Ride",
            25,
            false,
            LocalDate.now().plusWeeks(1),
            12.5,
            "Start at the community park and loop around the river trail."
        );
        seedOrganizer.createRace(
            LocalDate.now().plusWeeks(3),
            "State Championship Road Race",
            50,
            true,
            LocalDate.now().plusWeeks(2),
            42.0,
            "Rolling highway route with one categorized climb."
        );

        Race fullRace = seedOrganizer.createRace(
            LocalDate.now().plusWeeks(1),
            "Sold Out Criterium",
            1,
            false,
            LocalDate.now().plusDays(3),
            18.0,
            "Closed downtown loop."
        );
        fullRace.registerParticipant(new Racer("4000000000000002", "Full Race Placeholder"));
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
