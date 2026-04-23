package model.race;

import java.time.LocalDate;

public class RaceLicense {

    private static int nextId = 1;

    private LocalDate expDate;
    private final int id;
    private int categoryLevel;

    public RaceLicense() {
        this(5);
    }

    public RaceLicense(int categoryLevel) {
        this(LocalDate.now().plusYears(1), nextId++, categoryLevel);
    }

    public RaceLicense(LocalDate expDate, int id, int categoryLevel) {
        this.expDate = expDate;
        this.id = id;
        this.categoryLevel = categoryLevel;
    }

    public void manageLicenses(int categoryLevel) {
        expDate = LocalDate.now().plusYears(1);
        this.categoryLevel = categoryLevel;
    }

    public LocalDate getExpDate() {
        return expDate;
    }

    public int getId() {
        return id;
    }

    public int getCategoryLevel() {
        return categoryLevel;
    }

    public boolean isActive() {
        return !expDate.isBefore(LocalDate.now());
    }
}
