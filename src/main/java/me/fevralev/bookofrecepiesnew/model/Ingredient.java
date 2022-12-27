package me.fevralev.bookofrecepiesnew.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"measureUnit", "count"})
@NoArgsConstructor
public class Ingredient {
    private String title;
    private int count;
    private String measureUnit;

}
