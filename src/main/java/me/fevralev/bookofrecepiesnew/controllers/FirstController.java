package me.fevralev.bookofrecepiesnew.controllers;

import me.fevralev.bookofrecepiesnew.services.CounterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {
    private final CounterService counterService;

    public FirstController(CounterService counterService) {
        this.counterService = counterService;
    }

    @GetMapping("/greetings")
    public String showGreetings(@RequestParam(required = true) String name, @RequestParam(required = false) String surname) {
        return "Привет, " + name + " " + surname + "!";
    }

    @GetMapping()
    public String StartPage() {
        return "Приложение запущено! ";
    }

    @GetMapping("/info")
    public String Description() {
        return "<center>Студент Февралев Антон.\n" +
                "<br>Проект \"Книга рецептов бабы Любы\".\n" +
                "Проект создан 08.12.2022." +
                "\nДанный проект предназначен для начинающих кулинаров.\n" +
                "Постоен на фреймворке Spring. Язык программирования Java v17. Собран сборщиком Maven. Число запросов " + counterService.getRequestCount();
    }
}