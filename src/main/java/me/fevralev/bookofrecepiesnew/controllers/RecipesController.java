package me.fevralev.bookofrecepiesnew.controllers;

import me.fevralev.bookofrecepiesnew.model.Recipe;
import me.fevralev.bookofrecepiesnew.services.impl.RecipeServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recipes")
public class RecipesController {
    private final RecipeServiceImpl recipeService;

    public RecipesController(RecipeServiceImpl recipeService) {
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
        if(recipe == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipe);
    }

}
