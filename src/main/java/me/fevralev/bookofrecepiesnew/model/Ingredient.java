package me.fevralev.bookofrecepiesnew.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"measureUnit", "count"})
public class Ingredient {
    private String title;
    private int count;
    private String measureUnit;

}
