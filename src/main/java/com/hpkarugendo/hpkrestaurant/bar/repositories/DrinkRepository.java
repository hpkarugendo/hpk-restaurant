package com.hpkarugendo.hpkrestaurant.bar.repositories;

import com.hpkarugendo.hpkrestaurant.bar.enums.Menu;
import com.hpkarugendo.hpkrestaurant.bar.models.Drink;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DrinkRepository extends CrudRepository<Drink, Integer> {
    List<Drink> findAll();
    void deleteById(int id);
    Optional<Drink> findFirstByName(String name);
    List<Drink> findByMenu(Menu menu);
}
