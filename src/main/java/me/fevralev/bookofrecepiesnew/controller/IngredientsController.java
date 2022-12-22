package me.fevralev.bookofrecepiesnew.controller;

import me.fevralev.bookofrecepiesnew.model.Ingredient;
import me.fevralev.bookofrecepiesnew.service.IngredientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<Ingredient>>getAll(@RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "5") int count) {
        List<Ingredient> list  = ingredientService.getAll(page, count);
              return ResponseEntity.ok(list);
    }
    @PutMapping("{id}")
    public ResponseEntity edit(@PathVariable int id, @RequestBody Ingredient ingredient) {
        Ingredient editedIngredient = ingredientService.edit(id, ingredient);
        if (editedIngredient == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(editedIngredient);
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable int id) {
        Ingredient ingredient = ingredientService.delete(id);
        if (ingredient == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("Объект удален");
    }

}
