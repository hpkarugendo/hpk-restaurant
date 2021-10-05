package com.hpkarugendo.hpkrestaurant.kitchen.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodDTO {
    private String name, desc, menu, course, date;
    private double price;
    private List<String> allergies;
}
