package me.fevralev.bookofrecepiesnew.service;

import me.fevralev.bookofrecepiesnew.model.Ingredient;
import org.springframework.data.domain.Page;

public interface IngredientService {


    Ingredient add(Ingredient ingr);

    Ingredient edit(int id, Ingredient ingredient);

    Ingredient getById(int id);

    Ingredient delete(int id);

    Page<Ingredient> getAll(int currentPage, int objects);
}
