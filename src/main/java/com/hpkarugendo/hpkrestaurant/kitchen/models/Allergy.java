package com.hpkarugendo.hpkrestaurant.kitchen.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Allergy {
    @Id
    @GeneratedValue
    private int id;
    @Column(unique = true)
    private String name;
    @Column(unique = true)
    private int code;
    @ManyToMany(mappedBy = "allergies")
    private List<Food> foods;
}
