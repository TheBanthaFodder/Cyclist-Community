package model.race;

import java.util.ArrayList;
import java.util.List;

import model.observer.Observer;
import model.observer.Subject;
import model.user.Racer;

public class Category extends Subject {

    private static final List<Category> allCategories = new ArrayList<>();

    static {
        allCategories.add(new Category(5));
        allCategories.add(new Category(4));
        allCategories.add(new Category(3));
        allCategories.add(new Category(2));
        allCategories.add(new Category(1));
    }

    private final List<Observer> allObservers;
    private final int level;

    private Category(int level) {
        this.allObservers = new ArrayList<>();
        this.level = level;
    }

    public void levelUp() {
        checkForUpgrade();
    }

    public void checkForUpgrade() {
        for (Observer observer : new ArrayList<>(allObservers)) {
            if (observer instanceof Racer) {
                Racer racer = (Racer) observer;
                if (racer.readyForUpgrade()) {
                    upgradeRacer(racer);
                }
            }
        }
    }

    public static List<Category> getAllCategories() {
        return new ArrayList<>(allCategories);
    }

    public static Category getCategory(int level) {
        for (Category category : allCategories) {
            if (category.getLevel() == level) {
                return category;
            }
        }
        return null;
    }

    public static Category attachRacer(Racer racer) {
        for (Category category : allCategories) {
            category.detach(racer);
        }

        Category category = getCategory(racer.getCategory());
        if (category != null) {
            category.attach(racer);
        }
        return category;
    }

    public static Category getCategoryForRacer(Racer racer) {
        return getCategory(racer.getCategory());
    }

    public static Category upgradeRacer(Racer racer) {
        Category currentCategory = getCategoryForRacer(racer);
        if (currentCategory != null) {
            currentCategory.detach(racer);
        }

        racer.receiveCategoryUpdate();
        return attachRacer(racer);
    }

    public int getLevel() {
        return level;
    }

    public List<Observer> getObservers() {
        return allObservers;
    }

    @Override
    public void notifyObserver() {
        for (Observer observer : new ArrayList<>(allObservers)) {
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
