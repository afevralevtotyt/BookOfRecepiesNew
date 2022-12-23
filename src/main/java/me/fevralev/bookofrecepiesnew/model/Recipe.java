package me.fevralev.bookofrecepiesnew.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Recipe{
    private String title;
    private int cookingTime;
    private Ingredient[] ingredients;
    private String[] steps;
}
