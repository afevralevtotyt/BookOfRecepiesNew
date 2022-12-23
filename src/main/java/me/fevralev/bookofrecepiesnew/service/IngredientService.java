package me.fevralev.bookofrecepiesnew.service;

import me.fevralev.bookofrecepiesnew.model.Ingredient;

import java.util.List;

public interface IngredientService {


    Ingredient add(Ingredient ingr);

    Ingredient edit(int id, Ingredient ingredient);

    Ingredient getById(int id);

    Ingredient delete(int id);

    List<Ingredient> getAll(int currentPage, int objects);
}
