package me.fevralev.bookofrecepiesnew.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.fevralev.bookofrecepiesnew.exception.FileDownloadException;
import me.fevralev.bookofrecepiesnew.exception.FileUploadException;
import me.fevralev.bookofrecepiesnew.service.FilesService;
import me.fevralev.bookofrecepiesnew.service.IngredientService;
import me.fevralev.bookofrecepiesnew.service.RecipeService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequestMapping("/files/")
public class FilesController {
    final private FilesService filesRecipesService;
    final private FilesService filesIngredientsService;
    final private RecipeService recipeService;
    final private IngredientService ingredientService;


    public FilesController(@Qualifier("filesRecipesServiceImpl") FilesService filesRecipesService,
                           @Qualifier("filesIngredientsServiceImpl") FilesService filesIngredientsService,
                           RecipeService recipeService,
                           IngredientService ingredientService) {
        this.filesRecipesService = filesRecipesService;
        this.filesIngredientsService = filesIngredientsService;
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
    }

    @Tag(name = "Выгрузить рецепты из файла JSON")
    @Operation(description = "Выберите файл с рецептами")
    @PostMapping(value = "/uploadRecipes", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Рецепты выгружены в приложение", content = @Content(
                    mediaType = "application/json"
            )),
            @ApiResponse(
                    responseCode = "500",
                    description = "Ошибка чтения файла"
            )
    })
    public ResponseEntity<String> uploadRecipes(@RequestParam MultipartFile file) {

        try {
            filesRecipesService.uploadFile(file);
            recipeService.readFromFile();
        } catch (IOException e) {
            throw new FileUploadException("Ошибка выгрузки файла");
        }

        return ResponseEntity.ok().build();
    }

    @Tag(name = "Выгрузить ингредиенты из файла JSON")
    @Operation(description = "Выберите файл с ингредиентами")
    @PostMapping(value = "/uploadIngredients", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ингредиенты выгружены в приложение", content = @Content(
                    mediaType = "application/json"
            )),
            @ApiResponse(
                    responseCode = "500",
                    description = "Ошибка выгрузки файла"
            )
    })
    public ResponseEntity<String> uploadIngredients(@RequestParam MultipartFile file) {

        try {
            filesIngredientsService.uploadFile(file);
            ingredientService.readFromFile();
        } catch (IOException e) {
            throw new FileUploadException("Ошибка выгрузки файла");
        }
        return ResponseEntity.ok().build();
    }

    @Tag(name = "Скачать все рецепты в файл JSON")
    @Operation(description = "Нажмите Download file")
    @GetMapping(value = "/downloadRecipes")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Файл для загрузки готов", content = @Content(
                    mediaType = "application/json"
            )),
            @ApiResponse(
                    responseCode = "204",
                    description = "Нет контента"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Ошибка скачивания файла"
            )
    })
    public ResponseEntity<Object> downloadDataFile() {
        File file = filesRecipesService.getDataFile();
        if (file.exists()) {
            try {
                InputStreamResource inputStreamResource;
                inputStreamResource = new InputStreamResource(new FileInputStream(file));
                return ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .contentLength(file.length())
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"recipesData.json\"")
                        .body(inputStreamResource);

            } catch (FileNotFoundException e) {
                    throw new FileDownloadException("Ошибка скачивания файла");
            }
        }
        return ResponseEntity.noContent().build();
    }
    @Tag(name = "Скачать рецепты в текстовом формате")
    @Operation(description = "Нажмите Download file")
    @GetMapping("/download")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Файл для загрузки готов", content = @Content(
                    mediaType = "application/json"
            )),
            @ApiResponse(
                    responseCode = "204",
                    description = "Ошибка скачивания файла"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Ошибка скачивания файла"
            )
    })
    public ResponseEntity<Object> downloadRecipes() {
        try {
            Path path = recipeService.createRecipesFile();
            if (Files.size(path) == 0) {
                return ResponseEntity.noContent().build();
            }
            InputStreamResource inputStreamResource = new InputStreamResource(new FileInputStream(path.toFile()));

            return ResponseEntity.ok()
                    .contentType(MediaType.TEXT_PLAIN)
                    .contentLength(Files.size(path))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"recipes.txt\"")
                    .body(inputStreamResource);

        } catch (IOException e) {
            throw new FileDownloadException("Ошибка скачивания файла");
        }

    }
}

