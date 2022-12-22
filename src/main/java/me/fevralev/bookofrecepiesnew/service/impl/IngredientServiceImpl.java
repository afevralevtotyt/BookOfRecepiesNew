package me.fevralev.bookofrecepiesnew.service.impl;

import me.fevralev.bookofrecepiesnew.model.Ingredient;
import me.fevralev.bookofrecepiesnew.service.IngredientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public Ingredient edit(int id, Ingredient ingredient) {
        if (ingredientBook.containsKey(id)) {
            ingredientBook.put(id, ingredient);
            return ingredient;
        }
        return null;
    }

    @Override
    public Ingredient delete(int id) {
        if (ingredientBook.containsKey(id)) {
            return ingredientBook.remove(id);
        }
        return null;
    }

    @Override
    public Page<Ingredient> getAll(int currentPage, int objects) {
        ArrayList<Ingredient> ingredients =new ArrayList<>(ingredientBook.values());
            Page<Ingredient> ingredientPage;
            ingredientPage = new PageImpl<>(ingredients, PageRequest.of(currentPage, objects), ingredients.size());
            return ingredientPage;
        }
    }

