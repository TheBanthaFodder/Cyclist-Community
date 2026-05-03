package model.user;

public class UserContext {

    private UserStrategy strategy;

    public UserContext() {
    }

    public UserContext(UserStrategy strategy) {
        this.strategy = strategy;
    }

    // Sets the user role behavior, such as Racer, Organizer, or Administrator.
    public void setUserStrategy(UserStrategy strategy) {
        this.strategy = strategy;
    }

    // Returns the current user strategy so other classes can access role-specific behavior.
    public UserStrategy getUserStrategy() {
        return strategy;
    }

    public String getUserData() {
        return strategy.getUserData();
    }
}
