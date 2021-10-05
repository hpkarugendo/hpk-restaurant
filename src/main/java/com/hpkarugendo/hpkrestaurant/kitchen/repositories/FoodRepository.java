package com.hpkarugendo.hpkrestaurant.kitchen.repositories;

import com.hpkarugendo.hpkrestaurant.kitchen.enums.Menu;
import com.hpkarugendo.hpkrestaurant.kitchen.models.Food;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface FoodRepository extends CrudRepository<Food, Integer> {
    Optional<Food> findByName(String name);
    List<Food> findAll();
    List<Food> findBySpecialMenuDate(LocalDate specialMenuDate);
    List<Food> findByMenu(Menu menu);
    void deleteById(int id);
}
