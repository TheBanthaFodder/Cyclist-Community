package model.user;

public class UserContext {

    private UserStrategy strategy;

    public UserContext() {
    }

    public UserContext(UserStrategy strategy) {
        this.strategy = strategy;
    }

    public void setUserStrategy(UserStrategy strategy) {
        this.strategy = strategy;
    }

    public UserStrategy getUserStrategy() {
        return strategy;
    }

    public String getUserData() {
        return strategy.getUserData();
    }
}
