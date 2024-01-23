package com.example.db.controller;

import com.example.db.model.AutomobileMark;
import com.example.db.model.AutomobileModel;
import com.example.db.model.Equipment;
import com.example.db.service.autoMark.AutomobileMarkService;
import com.example.db.service.autoModel.AutomobileModelService;
import com.example.db.service.equipment.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/auto_mark")

public class AutomobileMarkController {
    @Autowired
    private final AutomobileMarkService automobileMarkService;

    private final EquipmentService equipmentService;
    private final AutomobileModelService automobileModelService;

    public AutomobileMarkController(AutomobileMarkService automobileMarkService, EquipmentService equipmentService, AutomobileModelService automobileModelService) {
        this.automobileMarkService = automobileMarkService;
        this.equipmentService = equipmentService;
        this.automobileModelService = automobileModelService;
    }

    @GetMapping
    public String getAll(Model model){
        List<AutomobileMark> automobileMarks = automobileMarkService.getAllAutoMarks();
        model.addAttribute("automobileMark", automobileMarks);
        return "/automobileMark/auto_mark_list";
    }

    @PostMapping("/delete/{id}")
    public String DeleteAutoMark(@PathVariable Long id){
        automobileMarkService.deleteAutoMark(id);
        return "redirect:/auto_mark";
    }

    @GetMapping("/{id}")
    public String getAutoMarkById(@PathVariable Long id,
                                  Model model){
        AutomobileMark automobileMark = automobileMarkService.getAutoMarkById(id);
        List<AutomobileModel> automobileModels = automobileModelService.getAllAutoModels();
        model.addAttribute("automobileModels", automobileModels);
        model.addAttribute("automobileMark", automobileMark);

        return "/automobileMark/edit_auto_mark";
    }
    @PostMapping("/{id}")
    public String editAutoMarkById(@PathVariable Long id,
                                   @ModelAttribute AutomobileMark automobileMark) {
        AutomobileMark existingAutoMark = automobileMarkService.getAutoMarkById(id);
        existingAutoMark.setName(automobileMark.getName());

        for (AutomobileModel automobileModel : existingAutoMark.getAutomobileModels()) {
            automobileModel.setAutomobileMark(existingAutoMark);
        }

        automobileMarkService.editAutoMark(id, existingAutoMark);
        return "redirect:/auto_mark";
    }

    @GetMapping("/add")
    public String addAutoMark(Model model){
        List<AutomobileModel> automobileModels = automobileModelService.getAllAutoModels();
        model.addAttribute("automobileModels", automobileModels);
        return "/automobileMark/add_auto_mark";
    }

    @PostMapping("/add")
    public String addAutoMark(@ModelAttribute AutomobileMark automobileMark){
        automobileMarkService.writeAutoMark(automobileMark);
        return "redirect:/auto_mark";
    }

    @GetMapping("/sql/{id}")
    public String getSqlAutoMark(@PathVariable Long id,
                              Model model){
        List<Equipment> equipments =  automobileMarkService.getEquipmentByAutomobileMarkId(id);
        AutomobileMark automobileMark = automobileMarkService.getAutoMarkById(id);
        model.addAttribute("autoMark", automobileMark);
        model.addAttribute("equips", equipments);
        return "/automobileMark/get_sql_auto_mark";
    }

    @PostMapping("/sql/{id}")
    public String sqlAutoMark(@PathVariable Long id,
                              @ModelAttribute Equipment equipment,
                              Model model){
        Equipment equipments = equipmentService.getEquipmentById(id);
        List<AutomobileMark> automobileMark = automobileMarkService.getAllAutoMarks();
        model.addAttribute("equip", equipments);
        model.addAttribute("autoMarks", automobileMark);
        return "/automobileMark/get_sql_auto_mark";
    }

}
