package com.example.db.controller;

import com.example.db.model.Automobile;
import com.example.db.model.AutomobileMark;
import com.example.db.model.AutomobileModel;
import com.example.db.model.Equipment;
import com.example.db.service.auto.AutomobileService;
import com.example.db.service.autoMark.AutomobileMarkService;
import com.example.db.service.autoModel.AutomobileModelService;
import com.example.db.service.equipment.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/auto")
public class AutomobileController {

    private final AutomobileService automobileService;
    private final AutomobileMarkService automobileMarkService;
    private final AutomobileModelService automobileModelService;
    private final EquipmentService equipmentService;

    @Autowired
    public AutomobileController(AutomobileService automobileService,
                                AutomobileMarkService automobileMarkService,
                                AutomobileModelService automobileModelService,
                                EquipmentService equipmentService) {
        this.automobileService = automobileService;
        this.automobileMarkService = automobileMarkService;
        this.automobileModelService = automobileModelService;
        this.equipmentService = equipmentService;
    }

   @GetMapping
    public String getAll(Model model){
        List<Automobile> automobiles = automobileService.getAllAuto();
        model.addAttribute("automobiles", automobiles);
        return "/automobile/auto_list";
    }

    @PostMapping("/delete/{id}")
    public String DeleteAuto(@PathVariable Long id){
        automobileService.deleteAuto(id);
        return "redirect:/auto";
    }

    @GetMapping("/{id}")
    public String getAutoById(@PathVariable Long id,
                              Model model){
        Automobile automobile = automobileService.getAutoById(id);

        /*List<AutomobileMark> automobileMarks = automobileMarkService.getAutomobileMarksWithoutAutomobiles();*/
        List<AutomobileMark> automobileMarks = automobileMarkService.getAllAutoMarks();
        List<AutomobileModel> automobileModels = automobileModelService.getAllAutoModels();
        List<Equipment> equipments = equipmentService.getAllEquipment();

       /* List<AutomobileMark> allAutomobileMarks = new ArrayList<>(automobileMarks);
        allAutomobileMarks.add(automobile.getAutomobileMark());*/

        model.addAttribute("automobileMarks", automobileMarks);
        model.addAttribute("automobileModels", automobileModels);
        model.addAttribute("equipments", equipments);
        model.addAttribute("automobile", automobile);
        return "/automobile/edit_auto";
    }
    @PostMapping("/{id}")
    public String editAutoById(@PathVariable Long id,
                               @ModelAttribute Automobile automobile){
        automobileService.editAuto(id, automobile);
        return "redirect:/auto";
    }


    @GetMapping("/add")
    public String addAutomobile(Model model){
        List<AutomobileModel> automobileModels = automobileModelService.getAllAutoModels();
        List<Equipment> equipments = equipmentService.getAllEquipment();
        List<AutomobileMark> automobileMarks = automobileMarkService.getAllAutoMarks();
        model.addAttribute("automobileMarks", automobileMarks);
        model.addAttribute("automobileModels", automobileModels);
        model.addAttribute("equipments", equipments);
        return "/automobile/add_auto";
    }

    @PostMapping("/add")
    public String addAutomobile(@ModelAttribute Automobile automobile){
        automobileService.writeAuto(automobile);
        return "redirect:/auto";
    }
}
