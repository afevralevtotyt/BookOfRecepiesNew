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

    @Tag(name = "Добавить ингредиент")
    @Operation(description = "В теле запроса в формате JSON введите новый ингредиент")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Объект создан", content = @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = Ingredient.class))
            )),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ошибка ввода"
            )
    })
    @PostMapping
    public ResponseEntity createIngredient(@RequestBody Ingredient ingredient) {
        Ingredient createdRecipe = ingredientService.add(ingredient);
        if (createdRecipe == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredient);

    }

    @Tag(name = "Получить ингредиент по его идентификатору")
    @Operation(description = "Введите id ингредиента")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успех", content = @Content(
                    mediaType = "application/json"
            )),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ошибка ввода")
    })
    @Parameters(value = {@Parameter(example = "0", name = "id", description = "ID ингредиента в книге")})
    @GetMapping("{id}")
    public ResponseEntity getIngredientById(@PathVariable int id) {
        Ingredient ingredient = ingredientService.getById(id);
        if (ingredient == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredient);
    }

    @Tag(name = "Получить список всех ингредиентов")
    @Operation(description = "Введите номер страницы, количество ингредиентов на странице")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успех", content = @Content(
                    mediaType = "application/json"
            ))
    })
    @Parameters(value = {@Parameter(example = "0", name = "page", description = "Номер страницы"),
            @Parameter(example = "5", name = "count", description = "Количество ингредиентов на странице")})
    @GetMapping
    public ResponseEntity<List<Ingredient>> getAll(@RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "5") int count) {
        List<Ingredient> list = ingredientService.getAll(page, count);
        return ResponseEntity.ok(list);
    }

    @Tag(name = "Отредактировать ингредиент по его идентификатору")
    @Operation(description = "Введите номер ингредиета и в теле запроса в формате JSON новый ингредиент")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Объект изменен", content = @Content(
                    mediaType = "application/json"
            )),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ингредиент не найден"
            )
    })
    @Parameters(value = {@Parameter(example = "0", name = "id", description = "Идентификатор редактируемого ингредиента")})
    @PutMapping("{id}")
    public ResponseEntity edit(@PathVariable int id, @RequestBody Ingredient ingredient) {
        Ingredient editedIngredient = ingredientService.edit(id, ingredient);
        if (editedIngredient == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(editedIngredient);
    }

    @Tag(name = "Удалить ингредиент по идентификатору")
    @Operation(description = "Введите номер ингредиента, который вы хотите удалить")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Объект удален", content = @Content(
                    mediaType = "application/json"
            )),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ингредиент не найден"
            )
    })
    @Parameters(value = {@Parameter(example = "0", name = "id", description = "Идентификатор удаляемого ингредиента")})
    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable int id) {
        Ingredient ingredient = ingredientService.delete(id);
        if (ingredient == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("Объект удален");
    }

}
