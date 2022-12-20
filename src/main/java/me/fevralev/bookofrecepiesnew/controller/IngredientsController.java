package me.fevralev.bookofrecepiesnew.controller;

import me.fevralev.bookofrecepiesnew.model.Ingredient;
import me.fevralev.bookofrecepiesnew.service.IngredientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/ingredients")
public class IngredientsController {

    private final IngredientService ingredientService;


    public IngredientsController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping
    public ResponseEntity createIngredient(@RequestBody Ingredient ingredient) {
        Ingredient createdRecipe = ingredientService.add(ingredient);
        return ResponseEntity.ok(ingredient);
    }


    @GetMapping("{id}")
    public ResponseEntity getUser(@PathVariable int id) {
        Ingredient ingredient = ingredientService.getById(id);
        if (ingredient == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredient);
    }

    @GetMapping("all")
    public ResponseEntity getAll() {
        Map<Integer, Ingredient> book = ingredientService.getAll();
        if (book == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(book);
    }

    @PutMapping("{id}")
    public ResponseEntity editIngredient(@PathVariable int id, @RequestBody Ingredient ingredient) {
        Ingredient ingredient1 = ingredientService.editIngredient(id, ingredient);
        if (ingredient == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredient1);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteIngredient(@PathVariable int id) {
        Ingredient ingredient = ingredientService.deleteIngredient(id);
        if (ingredient == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("Объект удален");
    }

}
