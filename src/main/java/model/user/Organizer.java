package main.java.model.user;

public class Organizer implements UserStrategy {
	
	private String name;

    public Organizer(String name) {
        this.name = name;
    }

    @Override
    public String getUserData() {
        return String.format("Organizer{name='%s'}", this.name);
    }
}
