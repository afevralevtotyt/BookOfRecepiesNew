package me.fevralev.bookofrecepiesnew.service;

import me.fevralev.bookofrecepiesnew.model.Ingredient;

import java.util.ArrayList;

public interface IngredientService {


    Ingredient add(Ingredient ingr);

    Ingredient edit(int id, Ingredient ingredient);

    Ingredient getById(int id);

    Ingredient delete(int id);

    ArrayList<Ingredient> getAll();
}
