package model.user;

/**
 * Strategy Pattern Interface
 * 
 * This interface defines a common behavior for all user types.
 * Each concrete user (Racer, Organizer, Administrator) will implement
 * this interface and provide their own version of how user data is retrieved.
 * 
 * This allows the system to dynamically switch behavior at runtime
 * depending on the type of user currently active.
 */

public interface UserStrategy {


    /**
     * Returns a formatted string representing user-specific data.
     * 
     * Each implementing class will define what data is important
     * and how it should be displayed.
     */

    String getUserData();
}
