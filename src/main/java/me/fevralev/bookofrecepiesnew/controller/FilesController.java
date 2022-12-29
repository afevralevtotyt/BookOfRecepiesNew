package me.fevralev.bookofrecepiesnew.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.fevralev.bookofrecepiesnew.exception.FileDownloadException;
import me.fevralev.bookofrecepiesnew.exception.FileUploadException;
import me.fevralev.bookofrecepiesnew.service.impl.FilesIngredientsServiceImpl;
import me.fevralev.bookofrecepiesnew.service.impl.FilesRecipesServiceImpl;
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

@RestController
@RequestMapping("/files/")
public class FilesController {
    final private FilesRecipesServiceImpl filesService;
    final private FilesIngredientsServiceImpl filesIngredientsService;

    public FilesController(FilesRecipesServiceImpl filesService, FilesIngredientsServiceImpl filesIngredientsService) {
        this.filesService = filesService;
        this.filesIngredientsService = filesIngredientsService;
    }
    @Tag(name = "Выгрузить рецепты из файла JSON")
    @Operation(description = "Выберите файл с рецептами")
    @PostMapping(value = "/uploadRecipes", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadRecipes(@RequestParam MultipartFile file){

        try {
            filesService.uploadFile(file);
        } catch (IOException e) {
            throw new FileUploadException();
        }
        return ResponseEntity.ok().build();
    }
    @Tag(name = "Выгрузить ингредиенты из файла JSON")
    @Operation(description = "Выберите файл с ингредиентами")
    @PostMapping(value = "/uploadIngredients", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadIngredients(@RequestParam MultipartFile file) throws IOException {
        filesIngredientsService.uploadFile(file);
        return ResponseEntity.ok().build();
    }

    @Tag(name = "Скачать все рецепты в файл JSON")
    @Operation(description = "Нажмите Download file")
    @GetMapping(value = "/downloadRecipes")
    public ResponseEntity<InputStreamResource> downloadDataFile() {
        File file = filesService.getDataFile();
        if (file.exists()) {
            InputStreamResource inputStreamResource;
            try {
                inputStreamResource = new InputStreamResource(new FileInputStream(file));
            } catch (FileNotFoundException e) {
                throw new FileDownloadException();
            }
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .contentLength(file.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"recipesData.json\"")
                    .body(inputStreamResource);
        }
        return ResponseEntity.noContent().build();
    }
}
