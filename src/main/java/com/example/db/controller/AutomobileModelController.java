package com.example.db.controller;

import com.example.db.model.Automobile;
import com.example.db.model.AutomobileMark;
import com.example.db.model.AutomobileModel;
import com.example.db.service.autoMark.AutomobileMarkService;
import com.example.db.service.autoModel.AutomobileModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/auto_model")
public class AutomobileModelController {

    @Autowired
    private final AutomobileModelService automobileModelService;
    private final AutomobileMarkService automobileMarkService;

    public AutomobileModelController(AutomobileModelService automobileModelService, AutomobileMarkService automobileMarkService) {
        this.automobileModelService = automobileModelService;
        this.automobileMarkService = automobileMarkService;
    }



    @GetMapping
    public String getAll(Model model){
        List<AutomobileModel> automobileModels = automobileModelService.getAllAutoModels();
        List<AutomobileMark> automobileMarks = automobileMarkService.getAllAutoMarks();
        model.addAttribute("automobileMarks", automobileMarks);
        model.addAttribute("automobileModels", automobileModels);
        return "/automobileModel/auto_model_list";
    }

    @PostMapping("/delete/{id}")
    public String DeleteAutoModel(@PathVariable Long id){
        automobileModelService.deleteAutoModel(id);
        return "redirect:/auto_model";
    }

    @GetMapping("/{id}")
    public String getAutoModelById(@PathVariable Long id,
                                   Model model){
        List<AutomobileMark> automobileMarks = automobileMarkService.getAllAutoMarks();
        AutomobileModel automobileModel = automobileModelService.getAutoModelById(id);
        model.addAttribute("automobileMarks", automobileMarks);
        model.addAttribute("automobileModel", automobileModel);
        return "/automobileModel/edit_auto_model";
    }
    @PostMapping("/{id}")
    public String editAutoModelById(@PathVariable Long id, @ModelAttribute AutomobileModel automobileModel) {
        AutomobileModel existingAutoModel = automobileModelService.getAutoModelById(id);
        existingAutoModel.setName(automobileModel.getName());
        existingAutoModel.setAutomobileMark(automobileModel.getAutomobileMark()); // Обновление марки автомобиля

        automobileModelService.editAutoModel(id, existingAutoModel);
        return "redirect:/auto_model";
    }

    @GetMapping("/add")
    public String addAutomobileModel(Model model){
        List<AutomobileMark> automobileMarks = automobileMarkService.getAllAutoMarks();
        model.addAttribute("automobileMarks", automobileMarks );
        return "/automobileModel/add_auto_model";
    }

    @PostMapping("/add")
    public String addAutoModel(@ModelAttribute AutomobileModel automobileModel){
        automobileModelService.writeAutoModel(automobileModel);
        return "redirect:/auto_model";
    }

    @GetMapping("/sql")
    public String addAutomobileModels(Model model){
        List<Object[]> automobileModels = automobileModelService.findMarksAndEquipsWithSalesThanTwo();
        model.addAttribute("automobileModels", automobileModels);
        System.out.println(automobileModels);
        return "/automobileModel/get_sql_auto_model";
    }

}
