package me.fevralev.bookofrecepiesnew.model;

import lombok.*;

@Data
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"measureUnit", "count"})
@NoArgsConstructor
public class Ingredient {
    private String title;
    private int count;
    private String measureUnit;

    @Override
    public String toString() {
        return  "·" +title + "-" + count +" "+
                measureUnit +"\n";
    }
}
