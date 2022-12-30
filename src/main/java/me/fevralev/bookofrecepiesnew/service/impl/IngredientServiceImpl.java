package me.fevralev.bookofrecepiesnew.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.fevralev.bookofrecepiesnew.exception.FileReadException;
import me.fevralev.bookofrecepiesnew.exception.FileWriteException;
import me.fevralev.bookofrecepiesnew.model.Ingredient;
import me.fevralev.bookofrecepiesnew.service.FilesService;
import me.fevralev.bookofrecepiesnew.service.IngredientService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IngredientServiceImpl implements IngredientService {
    final private FilesService filesIngredientsService;
    private int id = 0;
    public static Map<Integer, Ingredient> ingredientBook = new HashMap<>();

    public IngredientServiceImpl(@Qualifier("filesIngredientsServiceImpl")FilesService filesIngredientsService) {
        this.filesIngredientsService = filesIngredientsService;
    }

    @PostConstruct
    public void init() {
        try {
            readFromFile();
        }
        catch (FileReadException e){
            e.printStackTrace();
        }
    }
    public Ingredient add(Ingredient ingr) {
        if (StringUtils.isNotEmpty(ingr.getTitle()) && StringUtils.isNotEmpty(ingr.getMeasureUnit()) && ingr.getCount() > 0) {
            ingredientBook.put(id++, ingr);
            saveToFile();
            return ingr;
        } else return null;
    }

    @Override
    public Ingredient getById(int id) {
        return ingredientBook.get(id);
    }

    @Override
    public Ingredient edit(int id, Ingredient ingredient) {
        if (ingredientBook.containsKey(id)) {
            ingredientBook.put(id, ingredient);
            saveToFile();
            return ingredient;
        }

        return null;
    }

    @Override
    public Ingredient delete(int id) {
        if (ingredientBook.containsKey(id)) {
            Ingredient ingredient = ingredientBook.remove(id);
            saveToFile();
            return ingredient;
        }

        return null;
    }

    @Override
    public List<Ingredient> getAll(int currentPage, int count) {
        ArrayList<Ingredient> ingredientList = new ArrayList<>(ingredientBook.values());
        PagedListHolder<Ingredient> pagination = new PagedListHolder<>(ingredientList);
        pagination.setPageSize(count);
        pagination.setPage(currentPage);
        return pagination.getPageList();
    }

    @Override
    public void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(ingredientBook);
            filesIngredientsService.saveToFile(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new FileWriteException("Ошибка записи в файл");
        }
    }

    @Override
    public void readFromFile() {
        try {
            String json = filesIngredientsService.readFromFile();
            ingredientBook = new ObjectMapper().readValue(json, new TypeReference<HashMap<Integer, Ingredient>>() {
            });
        } catch (JsonProcessingException e) {
            throw new FileReadException("Ошибка чтения файла");
        }
    }
}

