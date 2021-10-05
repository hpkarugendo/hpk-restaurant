package com.hpkarugendo.hpkrestaurant.restaurant.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RestaurantController {
    @GetMapping("restaurant/home")
    public String kitchen(){
        return "home_restaurant";
    }
}
