package com.hpkarugendo.hpkrestaurant.kitchen.services;

import com.hpkarugendo.hpkrestaurant.kitchen.models.Allergy;
import com.hpkarugendo.hpkrestaurant.kitchen.repositories.AllergyRepository;
import com.hpkarugendo.hpkrestaurant.kitchen.repositories.FoodRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AllergyService {
    private final AllergyRepository aRepo;
    private final FoodRepository fRepo;

    public AllergyService(AllergyRepository aRepo, FoodRepository fRepo) {
        this.aRepo = aRepo;
        this.fRepo = fRepo;
    }

    public List<Allergy> listAll(){
        return aRepo.findAll();
    }

    public Allergy getByName(String name){
        Optional<Allergy> ans = aRepo.findByName(name);
        if(ans.isPresent()){
            return ans.get();
        } else{
            return null;
        }
    }

    public Allergy saveAllergy(Allergy allergyModel){
        if(getByName(allergyModel.getName()) == null){
            if(listAll().isEmpty()){
                allergyModel.setCode(1);
            } else {
                allergyModel.setCode(aRepo.findFirstByOrderByCodeDesc().getCode() + 1);
            }
            return aRepo.save(allergyModel);
        } else {
            return getByName(allergyModel.getName());
        }
    }

    public void deleteAllergy(int id){
        try{
            aRepo.deleteById(id);
        } catch (Exception e){
            throw new RuntimeException("Error Deleting Allergy With ID: " + id + "!");
        }
    }

    public List<Allergy> getAllExcept(String name){
        return aRepo.findByNameNot(name);
    }
}
