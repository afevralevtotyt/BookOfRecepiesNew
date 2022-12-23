package me.fevralev.bookofrecepiesnew.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.fevralev.bookofrecepiesnew.service.CounterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Стартовый контроллер", description = "Содержит описание данного проекта")
public class FirstController {
    private final CounterService counterService;

    public FirstController(CounterService counterService) {
        this.counterService = counterService;
    }
    @GetMapping("/greetings")
    @Operation(description = "Приветственное слово")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Добро пожаловать")
    })
    public String showGreetings(@RequestParam(required = true) String name, @RequestParam(required = false) String surname) {
        return "Привет, " + name + " " + surname + "!";
    }

    @Operation(description = "Стартовая страница")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Добро пожаловать")})
    @GetMapping()
    public String StartPage() {
        return "Приложение запущено! ";
    }


    @Operation(description = "Информация о проекте")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Теперь вы знаете большую тайну!",
            content = {
                    @Content(
                            mediaType = "text/plain;charset=UTF-8")
                    })
    })
    @GetMapping("/info")
    public String Description() {
        return "<center>Студент Февралев Антон.\n" +
                "<br>Проект \"Книга рецептов бабы Любы\".\n" +
                "Проект создан 08.12.2022." +
                "\nДанный проект предназначен для начинающих кулинаров.\n" +
                "Постоен на фреймворке Spring. Язык программирования Java v17. Собран сборщиком Maven. Число запросов " + counterService.getRequestCount();
    }
}