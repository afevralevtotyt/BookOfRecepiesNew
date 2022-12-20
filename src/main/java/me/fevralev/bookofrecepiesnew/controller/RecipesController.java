package me.fevralev.bookofrecepiesnew.controller;

import me.fevralev.bookofrecepiesnew.model.Ingredient;
import me.fevralev.bookofrecepiesnew.model.Recipe;
import me.fevralev.bookofrecepiesnew.service.IngredientService;
import me.fevralev.bookofrecepiesnew.service.impl.IngredientServiceImpl;
import me.fevralev.bookofrecepiesnew.service.impl.RecipeServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Map;

@RestController
@RequestMapping("/recipes")
public class RecipesController {
    private final RecipeServiceImpl recipeService;

    public RecipesController(RecipeServiceImpl recipeService, IngredientServiceImpl ingredientService) {
        this.recipeService = recipeService;
    }

    @PostMapping
    public ResponseEntity createRecipe(@RequestBody Recipe recipe) {
        Recipe createdRecipe = recipeService.add(recipe);
        return ResponseEntity.ok(recipe);
    }


    @GetMapping("{id}")
    public ResponseEntity getUser(@PathVariable int id) {
        Recipe recipe = recipeService.getById(id);
        if (recipe == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipe);
    }

    @GetMapping("all")
    public ResponseEntity getAll() {
        Map<Integer, Recipe> book = recipeService.getAll();
        if (book == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(book);
    }

    @PutMapping("{id}")
    public ResponseEntity editRecipe(@PathVariable int id, @RequestBody Recipe recipe) {
        Recipe recipe1 = recipeService.editRecipe(id, recipe);
        if (recipe1 == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipe1);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteRecipe(@PathVariable int id) {
        Recipe recipe = recipeService.deleteRecipe(id);
        if (recipe == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("Объект удален");
    }

}
