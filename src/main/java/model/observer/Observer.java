package model.observer;

/**
 * Observer Interface (Observer Pattern)
 * 
 * Any class that wants to be notified of updates
 * (such as category upgrades) must implement this interface.
 */

public interface Observer {

    /**
     * Called when the subject triggers an update.
     */
    void receiveCategoryUpdate();
}
