package com.hpkarugendo.hpkrestaurant.bar.models;

import com.hpkarugendo.hpkrestaurant.bar.enums.Menu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Drink {
    @Id
    @GeneratedValue
    private int id;
    @Column(unique = true)
    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    private Menu menu;
    private double bottle, shot, glass, pint, cup, deal;
}
