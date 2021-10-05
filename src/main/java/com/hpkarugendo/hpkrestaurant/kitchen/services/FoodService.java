package com.hpkarugendo.hpkrestaurant.kitchen.services;

import com.hpkarugendo.hpkrestaurant.kitchen.enums.Menu;
import com.hpkarugendo.hpkrestaurant.kitchen.models.Allergy;
import com.hpkarugendo.hpkrestaurant.kitchen.models.Food;
import com.hpkarugendo.hpkrestaurant.kitchen.repositories.FoodRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FoodService {
    private final FoodRepository fRepo;
    private final AllergyService aService;

    public FoodService(FoodRepository fRepo, AllergyService aService) {
        this.fRepo = fRepo;
        this.aService = aService;
    }

    public Food saveFood(Food foodModel){
        List<Allergy> allergies = new ArrayList<>();
        for(Allergy a: foodModel.getAllergies()){
            if(aService.getByName(a.getName()) == null){
                a.setCode(aService.listAll().size()+1);
            }
            allergies.add(aService.saveAllergy(a));
        }
        foodModel.setAllergies(allergies);
        return fRepo.save(foodModel);
    }

    public List<Food> listAll(){
        return fRepo.findAll();
    }

    public List<Food> listAllWithoutAllergy(String name){
        List<Food> foods = new ArrayList<>();
        for(Allergy a: aService.getAllExcept(name)){
            for(Food f: a.getFoods()){
                foods.add(f);
            }
        }
        return foods;
    }

    public List<Food> listAllSpecialForDate(LocalDate localDate){
        return fRepo.findBySpecialMenuDate(localDate);
    }

    public List<Food> listAllForMenu(Menu menu){
        return fRepo.findByMenu(menu);
    }

    public Food getFood(String name){
        Optional<Food> ans = fRepo.findByName(name);
        if(ans.isPresent()){
            return ans.get();
        } else {
            return null;
        }
    }

    public void deleteFood(int id){
        try {
            fRepo.deleteById(id);
        } catch (Exception e){
            throw new RuntimeException("Error Deleting Food With ID: " + id + "!");
        }
    }
}
