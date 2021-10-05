package com.hpkarugendo.hpkrestaurant.kitchen.models;

import com.hpkarugendo.hpkrestaurant.kitchen.enums.Course;
import com.hpkarugendo.hpkrestaurant.kitchen.enums.Menu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Food {
    @Id
    @GeneratedValue
    private int id;
    @Column(unique = true)
    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    private Course course;
    @Enumerated(EnumType.STRING)
    private Menu menu;
    private LocalDate specialMenuDate;
    private double price;
    @ManyToMany
    @JoinTable(
            name = "food_allergies",
            joinColumns = @JoinColumn(name = "food_id"),
            inverseJoinColumns = @JoinColumn(name = "allergy_id")
    )
    private List<Allergy> allergies;
}
