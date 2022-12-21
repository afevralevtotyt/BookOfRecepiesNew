package me.fevralev.bookofrecepiesnew.service;


import me.fevralev.bookofrecepiesnew.model.Ingredient;
import me.fevralev.bookofrecepiesnew.model.Recipe;

import java.util.ArrayList;
import java.util.HashSet;

public interface RecipeService {


    Recipe add(Recipe recipe);

    Recipe getById(int id);

    Recipe edit(int id, Recipe recipe);

    Recipe delete(int id);

    ArrayList<Recipe> getAll();

    HashSet<Recipe> getRecipeByIngredientId(int id);

    Recipe searchBySomeIngredients(Ingredient[] ingredients);
}
