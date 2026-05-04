package model.observer;

/**
 * Subject Abstract Class (Observer Pattern)
 * 
 * The Subject is responsible for:
 *  - Maintaining a list of observers
 *  - Notifying observers when a change occurs
 */

public abstract class Subject {

    /**
     * Notify all observers of a change.
     */
    public abstract void notifyObserver();

    /**
     * Add an observer to the list.
     */
    public abstract void attach(Observer observer);

    /**
     * Remove an observer from the list.
     */
    public abstract void detach(Observer observer);
}
