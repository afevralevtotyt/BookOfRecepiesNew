package me.fevralev.bookofrecepiesnew.model;

public class Ingredient {
    String title;
    int count;
    String measureUnit;

    public Ingredient(String title, int count, String measureUnit) {
        this.title = title;
        this.count = count;
        this.measureUnit = measureUnit;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getMeasureUnit() {
        return measureUnit;
    }

    public void setMeasureUnit(String measureUnit) {
        this.measureUnit = measureUnit;
    }
}
