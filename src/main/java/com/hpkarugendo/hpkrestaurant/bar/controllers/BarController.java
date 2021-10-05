package com.hpkarugendo.hpkrestaurant.bar.controllers;

import com.hpkarugendo.hpkrestaurant.bar.models.DrinkDTO;
import com.hpkarugendo.hpkrestaurant.bar.services.DrinkService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class BarController {
    private final DrinkService dService;

    public BarController(DrinkService dService) {
        this.dService = dService;
    }

    @GetMapping("bar/home")
    public String kitchen(){
        return "home_bar";
    }

    @GetMapping("manage/bar")
    public String manageBar(Model m){
        m.addAttribute("drinks", dService.listALl());
        return "manage_bar";
    }

    @GetMapping("manage/bar/drinks/new")
    public String newDrink(Model m){
        if(!m.containsAttribute("drinkDTO")){
            m.addAttribute("drinkDTO", new DrinkDTO());
        }
        return "manage_bar_form";
    }

    @PostMapping("manage/bar/drinks/add")
    public String addDrink(@ModelAttribute("drinkDTO") DrinkDTO drinkDTO, RedirectAttributes ra){
        try {
            if(drinkDTO.getId() > 0){
                dService.saveDrink(drinkDTO.getId(), drinkDTO);
            } else {
                dService.saveDrink(0, drinkDTO);
            }
            ra.addFlashAttribute("mYes", drinkDTO.getName().toUpperCase() + " saved Successfully");
            return "redirect:/manage/bar";
        }catch (Exception e){
            ra.addFlashAttribute("mNo", "Error Saving Drink. Try A different Name!");
            ra.addFlashAttribute("drinkDTO", drinkDTO);
            return "redirect:/manage/bar/drinks/new";
        }
    }

    @GetMapping("/manage/bar/drinks/change/{id}")
    public String changeDrink(@PathVariable("id") int id, RedirectAttributes ra){
        System.out.println("Getting Drink");
        DrinkDTO toChange = dService.getDrink(id);
        if(toChange == null){
            System.out.println("Could not get Drink!");
            ra.addFlashAttribute("mNo", "Can't find Drink With ID: " + id);
            return "redirect:/manage/bar";
        } else {
            System.out.println("Got Drink!");
            ra.addFlashAttribute("drinkDTO", toChange);
            return "redirect:/manage/bar/drinks/new";
        }
    }

    @GetMapping("manage/bar/drinks/delete/{id}")
    public String deleteDrink(@PathVariable("id") int id, RedirectAttributes ra){
        try {
            dService.deleteDrink(id);
            ra.addFlashAttribute("mYes", "You Deleted Drink with ID: " + id);
            return "redirect:/manage/bar";
        }catch (Exception e){
            ra.addFlashAttribute("mNo", "Error Deleting Drink With ID: " + id);
            return "redirect:/manage/bar";
        }
    }
}
