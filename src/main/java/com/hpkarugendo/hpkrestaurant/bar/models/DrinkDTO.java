package com.hpkarugendo.hpkrestaurant.bar.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DrinkDTO {
    private int id;
    private String name, desc, menu;
    private double bPrice, gPrice, sPrice, cPrice, pPrice, dPrice;
}
