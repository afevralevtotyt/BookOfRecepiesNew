package me.fevralev.bookofrecepiesnew.service;


import me.fevralev.bookofrecepiesnew.model.Ingredient;
import me.fevralev.bookofrecepiesnew.model.Recipe;

import java.util.HashSet;
import java.util.Map;

public interface RecipeService {


    Recipe add(Recipe recipe);

    Recipe getById(int id);

    Recipe editRecipe(int id, Recipe recipe);

    Recipe deleteRecipe(int id);

    Map<Integer, Recipe> getAll();

}
