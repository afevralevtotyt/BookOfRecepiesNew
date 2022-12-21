package me.fevralev.bookofrecepiesnew.controller;

import me.fevralev.bookofrecepiesnew.model.Ingredient;
import me.fevralev.bookofrecepiesnew.model.Recipe;
import me.fevralev.bookofrecepiesnew.service.impl.IngredientServiceImpl;
import me.fevralev.bookofrecepiesnew.service.impl.RecipeServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;

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

    @PostMapping("/search")
    public ResponseEntity createRecipe(@RequestBody Ingredient[] ingredients) {
        Recipe recipe = recipeService.searchBySomeIngredients(ingredients);
        if (recipe==null){
            return ResponseEntity.notFound().build();
        }
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

    @GetMapping
    public ResponseEntity getAll() {
        ArrayList<Recipe> book = recipeService.getAll();
        if (book.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(book);
    }
    @GetMapping("/search/{id}")
    public ResponseEntity getRecipeByIngredientId(@PathVariable int id){
        HashSet<Recipe> list = recipeService.getRecipeByIngredientId(id);
        if (list.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(list);
    }

    @PutMapping("{id}")
    public ResponseEntity edit(@PathVariable int id, @RequestBody Recipe recipe) {
        Recipe editedRecipe = recipeService.edit(id, recipe);
        if (editedRecipe == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(editedRecipe);
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable int id) {
        Recipe recipe = recipeService.delete(id);
        if (recipe == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("Объект удален");
    }

}
