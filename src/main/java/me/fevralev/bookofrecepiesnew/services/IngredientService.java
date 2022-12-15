package me.fevralev.bookofrecepiesnew.services;

import me.fevralev.bookofrecepiesnew.model.Ingredient;

public interface IngredientService {
    Ingredient add(Ingredient ingr);
    Ingredient getById(int id);

}
