package me.fevralev.bookofrecepiesnew.service;


import me.fevralev.bookofrecepiesnew.model.Ingredient;
import me.fevralev.bookofrecepiesnew.model.Recipe;

import java.util.HashSet;
import java.util.List;

public interface RecipeService {


    Recipe add(Recipe recipe);

    Recipe getById(int id);

    Recipe edit(int id, Recipe recipe);

    Recipe delete(int id);

    List<Recipe> getAll(int currentPage, int pages);

    HashSet<Recipe> getRecipeByIngredientId(int id);

    Recipe searchBySomeIngredients(Ingredient[] ingredients);

    void saveToFile();

    void readFromFile();
}
