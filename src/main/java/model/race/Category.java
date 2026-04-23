package model.race;

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

    @Override
    public void attach(Observer observer) {
        allObservers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        allObservers.remove(observer);
    }
}
