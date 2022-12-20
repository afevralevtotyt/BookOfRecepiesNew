package me.fevralev.bookofrecepiesnew.model;

import java.util.Objects;

public class Ingredient {
    private String title;
    private int count;
    private String measureUnit;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ingredient that)) return false;

        if (count != that.count) return false;
        if (!Objects.equals(title, that.title)) return false;
        return Objects.equals(measureUnit, that.measureUnit);
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + count;
        result = 31 * result + (measureUnit != null ? measureUnit.hashCode() : 0);
        return result;
    }
}
