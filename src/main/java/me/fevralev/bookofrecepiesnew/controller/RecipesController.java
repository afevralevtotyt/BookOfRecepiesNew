package me.fevralev.bookofrecepiesnew.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.fevralev.bookofrecepiesnew.model.Ingredient;
import me.fevralev.bookofrecepiesnew.model.Recipe;
import me.fevralev.bookofrecepiesnew.service.impl.IngredientServiceImpl;
import me.fevralev.bookofrecepiesnew.service.impl.RecipeServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipes")
public class RecipesController {
    private final RecipeServiceImpl recipeService;

    public RecipesController(RecipeServiceImpl recipeService, IngredientServiceImpl ingredientService) {
        this.recipeService = recipeService;
    }

    @Tag(name = "Добавить рецепт")
    @Operation(description = "В теле запроса в формате JSON введите новый рецепт")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Объект создан", content = @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = Recipe.class))
            )),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ошибка ввода!"
            )
    })
    @PostMapping
    public ResponseEntity createRecipe(@RequestBody Recipe recipe) {
        Recipe createdRecipe = recipeService.add(recipe);
        if (createdRecipe != null) {
            return ResponseEntity.ok(recipe);
        }
        return ResponseEntity.notFound().build();
    }

    @Tag(name = "Поиск рецепта по нескольким ингредиетам")
    @Operation(description = "В теле запроса в формате JSON введите список ингредиентов")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успех", content = @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = Recipe.class))
            )),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ошибка ввода"
            )
    })
    @PostMapping("/search")
    public ResponseEntity createRecipe(@RequestBody Ingredient[] ingredients) {
        Recipe recipe = recipeService.searchBySomeIngredients(ingredients);
        if (recipe == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipe);

    }

    @Tag(name = "Получить рецепт по его идентификатору")
    @Operation(description = "Введите id рецепта")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успех", content = @Content(
                    mediaType = "application/json"
            )),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ошибка ввода"
            )
    })
    @Parameters(value = {@Parameter(example = "0", name = "id", description = "ID рецепта в книге")})
    @GetMapping("{id}")
    public ResponseEntity getUser(@PathVariable int id) {
        Recipe recipe = recipeService.getById(id);
        if (recipe == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipe);
    }

    @Tag(name = "Получить список всех рецептов")
    @Operation(description = "Введите номер страницы, количество рецептов на странице")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успех", content = @Content(
                    mediaType = "application/json"
            ))
    })
    @Parameters(value = {@Parameter(example = "0", name = "page", description = "Номер страницы"),
            @Parameter(example = "5", name = "count", description = "Количество рецептов на странице")})
    @GetMapping
    public ResponseEntity<List<Recipe>> getAll(@RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "5") int count) {
        List<Recipe> result = recipeService.getAll(page, count);
        return ResponseEntity.ok(result);
    }

    @Tag(name = "Отредактировать рецепт по его идентификатору")
    @Operation(description = "Введите номер рецепта и в теле запроса в формате JSON новый рецепт")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Объект изменен", content = @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = Recipe.class))
            )),
            @ApiResponse(
                    responseCode = "404",
                    description = "Рецепт не найден"
            )
    })
    @Parameters(value = {@Parameter(example = "0", name = "id", description = "Идентификатор редактируемого рецепта")})
    @PutMapping("{id}")
    public ResponseEntity edit(@PathVariable int id, @RequestBody Recipe recipe) {
        Recipe editedRecipe = recipeService.edit(id, recipe);
        if (editedRecipe == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(editedRecipe);
    }

    @Tag(name = "Удалить рецепт по идентификатору")
    @Operation(description = "Введите номер рецепта, который вы хотите удалить")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Объект удален", content = @Content(
                    mediaType = "application/json"
            )),
            @ApiResponse(
                    responseCode = "404",
                    description = "Рецепт не найден"
            )
    })
    @Parameters(value = {@Parameter(example = "0", name = "id", description = "Идентификатор удаляемого рецепта")})
    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable int id) {
        Recipe recipe = recipeService.delete(id);
        if (recipe == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("Объект удален");
    }

}
