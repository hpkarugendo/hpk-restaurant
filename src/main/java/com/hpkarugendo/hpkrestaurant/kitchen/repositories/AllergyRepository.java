package com.hpkarugendo.hpkrestaurant.kitchen.repositories;

import com.hpkarugendo.hpkrestaurant.kitchen.models.Allergy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AllergyRepository extends CrudRepository<Allergy, Integer> {
    List<Allergy> findAll();
    Optional<Allergy> findByName(String name);
    List<Allergy> findByNameNot(String name);
    Allergy findFirstByOrderByCodeDesc();
    void deleteById(int id);
}
