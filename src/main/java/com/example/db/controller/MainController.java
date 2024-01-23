package com.example.db.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {


    @GetMapping
    public String viewMainPage(){

        return "/main";
    }

    @GetMapping("/auto_list")
    public String viewAutoPage(){
        return "/auto_list";
    }

    @GetMapping("/auto_mark_list")
    public String viewAutoMarkPage(){
        return "/auto_mark_list";
    }

    @GetMapping("/equipment_list")
    public String viewEquipmentPage(){
        return "/equipment_list";
    }

    @GetMapping("/auto_model_list")
    public String viewAutoModelPage(){
        return "/auto_model_list";
    }
}
