package me.fevralev.bookofrecepiesnew.services.impl;

import me.fevralev.bookofrecepiesnew.model.Recipe;
import me.fevralev.bookofrecepiesnew.services.RecipeService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RecipeServiceImpl implements RecipeService {
    private int id=0;
    private Map<Integer, Recipe> recipeBook = new HashMap<>();


    @Override
    public Recipe add(Recipe recipe) {
        recipeBook.put(id++, recipe);
        return recipe;
    }


    @Override
    public Recipe getById(int id) {
        return recipeBook.get(id);
    }
}
