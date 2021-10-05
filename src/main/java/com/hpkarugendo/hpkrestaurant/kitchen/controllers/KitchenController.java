package com.hpkarugendo.hpkrestaurant.kitchen.controllers;

import com.hpkarugendo.hpkrestaurant.kitchen.enums.Course;
import com.hpkarugendo.hpkrestaurant.kitchen.enums.Menu;
import com.hpkarugendo.hpkrestaurant.kitchen.models.Allergy;
import com.hpkarugendo.hpkrestaurant.kitchen.models.Food;
import com.hpkarugendo.hpkrestaurant.kitchen.models.FoodDTO;
import com.hpkarugendo.hpkrestaurant.kitchen.services.AllergyService;
import com.hpkarugendo.hpkrestaurant.kitchen.services.FoodService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
public class KitchenController {
    private FoodService fService;
    private AllergyService aService;

    public KitchenController(FoodService fService, AllergyService aService) {
        this.fService = fService;
        this.aService = aService;
    }

    @GetMapping("kitchen/home")
    public String kitchen(){
        return "home_kitchen";
    }

    @GetMapping("manage/kitchen")
    public String manageKitchen(Model m){
        m.addAttribute("foods", fService.listAll());
        m.addAttribute("allergies", aService.listAll());
        return "manage_kitchen";
    }

    @GetMapping("manage/kitchen/food/new")
    public String newFood(Model m){
        m.addAttribute("foodDTO", new FoodDTO());
        return "manage_kitchen_form";
    }

    @PostMapping("manage/kitchen/food/add")
    public String addFood(@ModelAttribute("foodTDO") FoodDTO foodDTO, RedirectAttributes ra){
        Food toSave = new Food();
        toSave.setName(foodDTO.getName());
        toSave.setDescription(foodDTO.getDesc());
        toSave.setPrice(foodDTO.getPrice());
        switch (foodDTO.getCourse()){
            case "STARTER":
                toSave.setCourse(Course.STARTER);
                break;
            case "MAIN":
                toSave.setCourse(Course.MAIN);
                break;
            case "DESSERT":
                toSave.setCourse(Course.DESSERT);
                break;
        }
        if(!foodDTO.getDate().isEmpty()){
            LocalDate ld = LocalDate.parse(foodDTO.getDate());
            toSave.setSpecialMenuDate(ld);
        }
        switch (foodDTO.getMenu()){
            case "LUNCH":
                toSave.setMenu(Menu.LUNCH);
                break;
            case "DINNER":
                toSave.setMenu(Menu.DINNER);
                break;
            case "SPECIAL":
                toSave.setMenu(Menu.SPECIAL);
                break;
        }
        List<Allergy> allergies = new ArrayList<>();
        for(String s: foodDTO.getAllergies()){
            if(!s.isEmpty()){
                Allergy a = new Allergy();
                a.setName(s);
                allergies.add(a);
            }
        }
        toSave.setAllergies(allergies);
        fService.saveFood(toSave);
        return "redirect:/manage/kitchen";
    }
}
