package com.hpkarugendo.hpkrestaurant.bar.services;

import com.hpkarugendo.hpkrestaurant.bar.enums.Menu;
import com.hpkarugendo.hpkrestaurant.bar.models.Drink;
import com.hpkarugendo.hpkrestaurant.bar.models.DrinkDTO;
import com.hpkarugendo.hpkrestaurant.bar.repositories.DrinkRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DrinkService {
    private final DrinkRepository dRepo;

    public DrinkService(DrinkRepository dRepo) {
        this.dRepo = dRepo;
    }

    public List<Drink> listALl(){
        return dRepo.findAll();
    }

    public List<Drink> listByMenu(String menu){
        List<Drink> ans = null;
        switch (menu){
            case "BEER":
                ans= dRepo.findByMenu(Menu.BEER);
                break;
            case "DEAL":
                ans= dRepo.findByMenu(Menu.DEAL);
                break;
            case "LIQUOR":
                ans = dRepo.findByMenu(Menu.LIQUOR);
                break;
            case "SPIRIT":
                ans = dRepo.findByMenu(Menu.SPIRIT);
                break;
            case "SOFT DRINK":
                ans = dRepo.findByMenu(Menu.SOFT_DRINK);
                break;
            case "TEA":
                ans = dRepo.findByMenu(Menu.TEA);
                break;
            case "COCKTAIL":
                ans = dRepo.findByMenu(Menu.COCKTAIL);
                break;
            case "COFFEE":
                ans = dRepo.findByMenu(Menu.COFFEE);
                break;
            case "PROSECCO":
                ans = dRepo.findByMenu(Menu.PROSECCO);
                break;
            case "CHAMPAGNE":
                ans = dRepo.findByMenu(Menu.CHAMPAGNE);
                break;
            case "RED WINE":
                ans = dRepo.findByMenu(Menu.RED_WINE);
                break;
            case "WHITE WINE":
                ans = dRepo.findByMenu(Menu.WHITE_WINE);
                break;
            default:
                ans = new ArrayList<>();
                break;
        }

        return ans;
    }

    public Drink saveDrink(int id, DrinkDTO drinkDTO){
        Drink toSave = new Drink();

        if(id > 0){
            toSave.setId(dRepo.findById(id).get().getId());
        }

        toSave.setName(drinkDTO.getName());
        toSave.setDescription(drinkDTO.getDesc());
        toSave.setCup(drinkDTO.getCPrice());
        toSave.setBottle(drinkDTO.getBPrice());
        toSave.setGlass(drinkDTO.getGPrice());
        toSave.setShot(drinkDTO.getSPrice());
        toSave.setPint(drinkDTO.getPPrice());
        toSave.setDeal(drinkDTO.getDPrice());
        switch (drinkDTO.getMenu()){
            case "BEER":
                toSave.setMenu(Menu.BEER);
                break;
            case "LIQUOR":
                toSave.setMenu(Menu.LIQUOR);
                break;
            case "DEAL":
                toSave.setMenu(Menu.DEAL);
                break;
            case "SPIRIT":
                toSave.setMenu(Menu.SPIRIT);
                break;
            case "SOFT DRINK":
                toSave.setMenu(Menu.SOFT_DRINK);
                break;
            case "TEA":
                toSave.setMenu(Menu.TEA);
                break;
            case "COCKTAIL":
                toSave.setMenu(Menu.COCKTAIL);
                break;
            case "COFFEE":
                toSave.setMenu(Menu.COFFEE);
                break;
            case "PROSECCO":
                toSave.setMenu(Menu.PROSECCO);
                break;
            case "CHAMPAGNE":
                toSave.setMenu(Menu.CHAMPAGNE);
                break;
            case "RED WINE":
                toSave.setMenu(Menu.RED_WINE);
                break;
            case "WHITE WINE":
                toSave.setMenu(Menu.WHITE_WINE);
                break;
        }
        return dRepo.save(toSave);
    }

    public DrinkDTO getDrink(int id){
        DrinkDTO drinkDTO = new DrinkDTO();
        Optional<Drink> ans = dRepo.findById(id);
        if(ans.isPresent()){
            drinkDTO.setId(ans.get().getId());
            drinkDTO.setName(ans.get().getName());
            switch (ans.get().getMenu()){
                case BEER -> drinkDTO.setMenu("BEER");
                case WHITE_WINE -> drinkDTO.setMenu("WHITE WINE");
                case RED_WINE -> drinkDTO.setMenu("RED WINE");
                case CHAMPAGNE -> drinkDTO.setMenu("CHAMPAGNE");
                case PROSECCO -> drinkDTO.setMenu("PROSECCO");
                case TEA -> drinkDTO.setMenu("TEA");
                case COCKTAIL -> drinkDTO.setMenu("COCKTAIL");
                case COFFEE -> drinkDTO.setMenu("COFFEE");
                case SOFT_DRINK -> drinkDTO.setMenu("SOFT DRINK");
                case SPIRIT -> drinkDTO.setMenu("SPIRIT");
                case LIQUOR -> drinkDTO.setMenu("LIQUOR");
                case DEAL -> drinkDTO.setMenu("DEAL");
            }
            drinkDTO.setDesc(ans.get().getDescription());
            drinkDTO.setBPrice(ans.get().getBottle());
            drinkDTO.setCPrice(ans.get().getCup());
            drinkDTO.setDPrice(ans.get().getDeal());
            drinkDTO.setGPrice(ans.get().getGlass());
            drinkDTO.setPPrice(ans.get().getPint());
            drinkDTO.setSPrice(ans.get().getShot());
            return drinkDTO;
        } else {
            return null;
        }
    }

    public void deleteDrink(int id){
        dRepo.deleteById(id);
    }
}
