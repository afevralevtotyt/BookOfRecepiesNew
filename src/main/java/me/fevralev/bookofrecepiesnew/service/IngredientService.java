package me.fevralev.bookofrecepiesnew.service;

import me.fevralev.bookofrecepiesnew.model.Ingredient;

import java.util.Map;

public interface IngredientService {


    Ingredient add(Ingredient ingr);

    Ingredient editIngredient(int id, Ingredient ingredient);

    Ingredient getById(int id);

    Ingredient deleteIngredient(int id);

    Map<Integer, Ingredient> getAll();
}
