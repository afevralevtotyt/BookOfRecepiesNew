package me.fevralev.bookofrecepiesnew.service;


import me.fevralev.bookofrecepiesnew.model.Ingredient;
import me.fevralev.bookofrecepiesnew.model.Recipe;
import org.springframework.data.domain.Page;

import java.util.HashSet;

public interface RecipeService {


    Recipe add(Recipe recipe);

    Recipe getById(int id);

    Recipe edit(int id, Recipe recipe);

    Recipe delete(int id);

    Page<Recipe> getAll(int currentPage, int pages);

    HashSet<Recipe> getRecipeByIngredientId(int id);

    Recipe searchBySomeIngredients(Ingredient[] ingredients);
}
