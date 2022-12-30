package me.fevralev.bookofrecepiesnew.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recipe {
    private String title;
    private int cookingTime;
    private Ingredient[] ingredients;
    private String[] steps;

    @Override
    public String toString() {
        return title + ": \n" + "Время приготовления: "+cookingTime+"\n"+"Ингредиенты:\n"+
                Arrays.toString(ingredients) +
                Arrays.toString(steps);
    }
}
