package main.java.model.user;

public class UserContext {
	
    private UserStrategy strategy;
    
    //set concrete strategy
    public void setUserStrategy(UserStrategy strategy) {
        this.strategy = strategy;
    }
    
    //return user data
    public String getUserData() {
        return strategy.getUserData();
    }
}
