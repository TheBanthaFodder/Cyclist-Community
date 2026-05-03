package model.user;

public class Administrator extends User {

    public Administrator(String name) {
        super(name);
    }

    @Override
    public String getUserData() {
        return String.format("Administrator{name='%s'}", getName());
    }
}
