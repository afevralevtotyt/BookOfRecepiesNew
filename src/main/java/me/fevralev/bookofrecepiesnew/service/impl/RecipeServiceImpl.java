package me.fevralev.bookofrecepiesnew.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.fevralev.bookofrecepiesnew.exception.FileReadException;
import me.fevralev.bookofrecepiesnew.exception.FileWriteException;
import me.fevralev.bookofrecepiesnew.model.Ingredient;
import me.fevralev.bookofrecepiesnew.model.Recipe;
import me.fevralev.bookofrecepiesnew.service.FilesService;
import me.fevralev.bookofrecepiesnew.service.RecipeService;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;

@Service
public class RecipeServiceImpl implements RecipeService {
    final private FilesService filesRecipesService;
    private int id = 0;
    private Map<Integer, Recipe> recipeBook = new HashMap<>();


    public RecipeServiceImpl(@Qualifier("filesRecipesServiceImpl") FilesService filesRecipesService) {
        this.filesRecipesService = filesRecipesService;
    }

    @PostConstruct
    public void init() {
        try {
            readFromFile();
        } catch (FileReadException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Recipe add(Recipe recipe) {
        if (StringUtils.isNotEmpty(recipe.getTitle()) && ArrayUtils.isNotEmpty(recipe.getIngredients()) &&
                ArrayUtils.isNotEmpty(recipe.getSteps()) && recipe.getCookingTime() > 0) {
            recipeBook.put(id++, recipe);
            saveToFile();
            return recipe;
        }
        return null;
    }

    @Override
    public Recipe getById(int id) {
        return recipeBook.get(id);
    }

    @Override
    public Recipe edit(int id, Recipe recipe) {
        if (recipeBook.containsKey(id)) {
            recipeBook.put(id, recipe);
            saveToFile();
            return recipe;
        }
        return null;
    }

    @Override
    public Recipe delete(int id) {
        if (recipeBook.containsKey(id)) {
            Recipe recipe = recipeBook.remove(id);
            saveToFile();
            return recipe;
        }
        return null;
    }

    @Override
    public List<Recipe> getAll(int currentPage, int count) {
        ArrayList<Recipe> recipeList = new ArrayList<>(recipeBook.values());
        PagedListHolder<Recipe> pagination = new PagedListHolder<>(recipeList);
        pagination.setPageSize(count);
        pagination.setPage(currentPage);
        return pagination.getPageList();
    }

    @Override
    public HashSet<Recipe> getRecipeByIngredientId(int id) {
        HashSet<Recipe> list = new HashSet<>();
        Ingredient ingredient = IngredientServiceImpl.ingredientBook.get(id);
        for (Recipe recipe : recipeBook.values()) {
            for (Ingredient ingredientFromRecipe : recipe.getIngredients()) {
                if (StringUtils.compare(ingredientFromRecipe.getTitle(), (ingredient.getTitle())) == 0) {
                    list.add(recipe);
                }
            }
        }
        return list;
    }

    @Override
    public Recipe searchBySomeIngredients(Ingredient[] ingredients) {
        for (Recipe recipe : recipeBook.values()) {
            ArrayList<Ingredient> list = new ArrayList<>(List.of(recipe.getIngredients()));
            if (list.containsAll(List.of(ingredients))) {
                return recipe;
            }
        }
        return null;
    }

    @Override
    public void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(recipeBook);
            filesRecipesService.saveToFile(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new FileWriteException("???????????? ???????????????? ??????????");
        }
    }

    @Override
    public void readFromFile() {
        try {
            String json = filesRecipesService.readFromFile();
            recipeBook = new ObjectMapper().readValue(json, new TypeReference<HashMap<Integer, Recipe>>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new FileReadException("???????????? ???????????? ??????????");

        }
    }

    @Override
    public Path createRecipesFile() throws IOException {
        Path path = filesRecipesService.createTempFile("recipes");
        for (Recipe value : recipeBook.values()) {
            try (Writer writer = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
                String ingredients = Arrays.toString(value.getIngredients())
                        .replace("[", "  ")
                        .replace("]", "")
                        .replace(',', ' ');
                int i = 1;
                StringBuilder steps = new StringBuilder();
                for (String val : value.getSteps()
                ) {
                    steps.append(i++).append(". " + val + "\n");
                }
                writer.append(value.getTitle().toUpperCase() + "\n??????????????????????:\n" + ingredients + "\n???????????????????? ??????????????????????????:\n" + steps);
                writer.append("\n");
            }
        }
        return path;
    }


}

