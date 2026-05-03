package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class CliView {

    private static final Scanner INPUT_SCANNER = new Scanner(System.in);

    private final List<String> errors;
    private String userInput;

    protected CliView() {
        this.errors = new ArrayList<>();
        this.userInput = "";
    }

    public String getUserInput() {
        return promptForInput("Enter input: ");
    }

    public String getUserInput(String prompt) {
        return promptForInput(prompt);
    }

    public void setUserInput(String userInput) {
        this.userInput = userInput;
    }

    public void viewErrors() {
        if (errors.isEmpty()) {
            printMessage("No errors.");
            return;
        }

        System.out.println("Errors:");
        for (String error : errors) {
            System.out.println("- " + error);
        }
        errors.clear();
    }

    public void addError(String error) {
        errors.add(error);
    }

    protected void printMessage(String message) {
        System.out.println(message);
    }

    protected String promptForInput(String prompt) {
        System.out.print(prompt);
        setUserInput(INPUT_SCANNER.nextLine());
        return userInput;
    }
}
