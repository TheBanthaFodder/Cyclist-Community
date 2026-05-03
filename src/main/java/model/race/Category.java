package model.race;


/**
 * Category Class
 * 
 * This class represents a racing category (Cat 5 -> Cat 1).
 * It acts as the Subject in the Observer pattern.
 */

import java.util.ArrayList;
import java.util.List;

import model.observer.Observer;
import model.observer.Subject;

public class Category extends Subject {

    private final List<Observer> allObservers;
    private int level;

    public Category() {
        this.allObservers = new ArrayList<>();
        this.level = 0;
    }

    public void levelUp() {
        level++;
        notifyObserver();
    }

    public int getLevel() {
        return level;
    }

    public List<Observer> getObservers() {
        return allObservers;
    }

    @Override
    public void notifyObserver() {
        for (Observer observer : allObservers) {
            observer.receiveCategoryUpdate();
        }
    }

    /**
     * Attach an observer (e.g., Racer)
     */
    @Override
    public void attach(Observer observer) {
        allObservers.add(observer);
    }

    /**
     * Remove an observer
     */
    @Override
    public void detach(Observer observer) {
        allObservers.remove(observer);
    }
}
