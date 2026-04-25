package main.java.model.observer;

public abstract class Subject {

    public abstract void notifyObserver();

    public abstract void attach(Observer observer);

    public abstract void detach(Observer observer);
}
