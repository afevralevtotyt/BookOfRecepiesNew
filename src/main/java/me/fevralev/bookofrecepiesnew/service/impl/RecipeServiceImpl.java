package me.fevralev.bookofrecepiesnew.service.impl;

import me.fevralev.bookofrecepiesnew.model.Ingredient;
import me.fevralev.bookofrecepiesnew.model.Recipe;
import me.fevralev.bookofrecepiesnew.service.RecipeService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RecipeServiceImpl implements RecipeService {
    private int id = 0;
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

    @Override
    public Recipe edit(int id, Recipe recipe) {
        if (recipeBook.containsKey(id)) {
            recipeBook.put(id, recipe);
            return recipe;
        }
        return null;
    }

    @Override
    public Recipe delete(int id) {
        if (recipeBook.containsKey(id)) {
            return recipeBook.remove(id);
        }
        return null;
    }

    @Override
    public ArrayList<Recipe> getAll() {
        ArrayList<Recipe> recipeList = new ArrayList<>();
        if (recipeBook.isEmpty()) {
            return null;
        }
        for (Recipe recipe: recipeBook.values()){
            recipeList.add(recipe);
        }
        return recipeList;
    }
    @Override
    public HashSet<Recipe> getRecipeByIngredientId(int id){
        HashSet<Recipe> list = new HashSet<>();
        Ingredient ingredient = IngredientServiceImpl.ingredientBook.get(id);
        for (Recipe recipe: recipeBook.values()){
            for (Ingredient ingredientFromRecipe: recipe.getIngredients()){
                if (ingredientFromRecipe.equals(ingredient)) {
                    list.add(recipe);}
                }
            }return list;}
    @Override
    public Recipe searchBySomeIngredients(Ingredient[] ingredients){
        for (Recipe recipe: recipeBook.values()){
            ArrayList<Ingredient> list = new ArrayList<>(List.of(recipe.getIngredients()));
            if (list.containsAll(List.of(ingredients))){
                return recipe;
            }
        }
        return null;
    }

    }

