package model.user;

public class Organizer extends User {

    public Organizer(String name) {
        super(name);
    }

    @Override
    public String getUserData() {
        return String.format("Organizer{name='%s'}", getName());
    }
}
