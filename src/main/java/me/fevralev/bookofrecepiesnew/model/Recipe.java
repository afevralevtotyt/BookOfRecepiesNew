package me.fevralev.bookofrecepiesnew.model;

public class Recipe {
    String title;
    int cookingTime;
    Ingredient[] ingredients;
    String[] steps;

    public Recipe(String title, int cookingTime, Ingredient[] ingredients, String[] steps) {
        this.title = title;
        this.cookingTime = cookingTime;
        this.ingredients = ingredients;
        this.steps = steps;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(int cookingTime) {
        this.cookingTime = cookingTime;
    }

    public Ingredient[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(Ingredient[] ingredients) {
        this.ingredients = ingredients;
    }

    public String[] getSteps() {
        return steps;
    }

    public void setSteps(String[] steps) {
        this.steps = steps;
    }
}
