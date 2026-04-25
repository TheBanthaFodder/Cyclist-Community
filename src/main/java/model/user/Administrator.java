package main.java.model.user;

public class Administrator implements UserStrategy {
	private final String name;

    public Administrator(String name) {
        this.name = name;
    }

    @Override
    public String getUserData() {
        return String.format("Administrator{name='%s'}", this.name);
    }
}
