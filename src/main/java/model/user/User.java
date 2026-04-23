package model.user;

public abstract class User implements UserStrategy {

    private final String name;

    protected User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getUserData() {
        return String.format("User{name='%s'}", name);
    }
}
