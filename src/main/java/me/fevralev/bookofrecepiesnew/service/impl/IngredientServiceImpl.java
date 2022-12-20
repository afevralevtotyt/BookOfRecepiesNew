package me.fevralev.bookofrecepiesnew.service.impl;

import me.fevralev.bookofrecepiesnew.model.Ingredient;
import me.fevralev.bookofrecepiesnew.service.IngredientService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class IngredientServiceImpl implements IngredientService {
    private int id = 0;
    public static Map<Integer, Ingredient> ingredientBook = new HashMap<>();

    @Override
    public Ingredient add(Ingredient ingr) {
        ingredientBook.put(id++, ingr);
        return ingr;
    }

    @Override
    public Ingredient getById(int id) {
        return ingredientBook.get(id);
    }

    @Override
    public Ingredient editIngredient(int id, Ingredient ingredient) {
        if (ingredientBook.containsKey(id)) {
            ingredientBook.put(id, ingredient);
            return ingredient;
        }
        return null;
    }

    @Override
    public Ingredient deleteIngredient(int id) {
        if (ingredientBook.containsKey(id)) {
            return ingredientBook.remove(id);
        }
        return null;
    }

    @Override
    public Map<Integer, Ingredient> getAll() {
        if (ingredientBook.isEmpty()) {
            return null;
        }
        return ingredientBook;
    }

}
