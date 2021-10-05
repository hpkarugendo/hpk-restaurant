package com.hpkarugendo.hpkrestaurant.security.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController implements ErrorController {

    @GetMapping("/")
    public String mainPage(){
        return "home_main";
    }

    @GetMapping("/login")
    public String login(){
        return "index";
    }

    @GetMapping("/login?error")
    public String loginError(){
        return "index";
    }

    @GetMapping("/errors")
    public String errorsPage(){
        return "errors_denied";
    }

    @GetMapping("/error")
    public String errors(HttpServletRequest req){
        Object status = req.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        int code = Integer.valueOf(status.toString());
        if(code == 404){
            return "404";
        } else {
            return "101";
        }
    }
}
