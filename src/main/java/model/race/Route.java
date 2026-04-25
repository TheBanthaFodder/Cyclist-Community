package main.java.model.race;

public class Route {

    private double miles;
    private String directions;

    public Route() {
        this(0.0, "");
    }

    public Route(double miles, String directions) {
        this.miles = miles;
        this.directions = directions;
    }

    public void addRoute() {
        miles = 10.0;
        directions = "Route details pending.";
    }

    public void addRoute(double miles, String directions) {
        this.miles = miles;
        this.directions = directions;
    }

    public String getRoute() {
        return String.format("%.1f miles - %s", miles, directions);
    }

    public void removeRoute() {
        miles = 0.0;
        directions = "";
    }

    public double getMiles() {
        return miles;
    }

    public String getDirections() {
        return directions;
    }
}
