package model.user;

/**
 * Context Class for Strategy Pattern
 * 
 * This class holds a reference to a UserStrategy object.
 * It allows the system to switch between different user types
 * (Racer, Organizer, Administrator) at runtime without changing code.
 */

public class UserContext {

    // Current strategy being used (Racer, Organizer, or Admin)
    private UserStrategy strategy;

    public UserContext() {
    }

    /**
     * Constructor to initialize the context with a specific strategy.
     */
    public UserContext(UserStrategy strategy) {
        this.strategy = strategy;
    }


    /**
     * Allows changing the strategy dynamically at runtime.
     */
    public void setUserStrategy(UserStrategy strategy) {
        this.strategy = strategy;
    }

    public UserStrategy getUserStrategy() {
        return strategy;
    }

    /**
     * Delegates the call to the current strategy.
     * This is where polymorphism happens.
     */
    public String getUserData() {
        return strategy.getUserData();
    }
}
