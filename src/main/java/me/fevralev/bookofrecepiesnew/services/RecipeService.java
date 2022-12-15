package me.fevralev.bookofrecepiesnew.services;


import me.fevralev.bookofrecepiesnew.model.Recipe;

public interface RecipeService {


    Recipe add(Recipe recipe);
    Recipe getById(int id);
}
