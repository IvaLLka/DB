package com.example.db.controller;

import com.example.db.model.Automobile;
import com.example.db.model.AutomobileMark;
import com.example.db.model.Equipment;
import com.example.db.service.equipment.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/equipment")
public class EquipmentController {

    @Autowired
    private final EquipmentService equipmentService;

    public EquipmentController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }
    @GetMapping()
    public String getAll(Model model){
        List<Equipment> equipments = equipmentService.getAllEquipment();
        model.addAttribute("equipments", equipments);
        return "/equipment/equipment_list";
    }

    @PostMapping("/delete/{id}")
    public String DeleteEquipment(@PathVariable Long id){
        equipmentService.deleteEquipment(id);
        return "redirect:/equipment";
    }

    @GetMapping("/{id}")
    public String getEquipmentById(@PathVariable Long id,
                                      Model model){
        Equipment equipment = equipmentService.getEquipmentById(id);
        model.addAttribute("equipment", equipment);
        return "/equipment/edit_equipment";
    }

    @PostMapping("/{id}")
    public String editEquipment(@PathVariable Long id,
                                   @ModelAttribute Equipment equipment) {
        Equipment existingEquipment = equipmentService.getEquipmentById(id);

        existingEquipment.setBodyColor(equipment.getBodyColor());
        existingEquipment.setInteriorColor(equipment.getInteriorColor());
        existingEquipment.setElectricWindows(equipment.getElectricWindows());
        existingEquipment.setInteriorTrim(equipment.getInteriorTrim());
        existingEquipment.setDualZoneClimate(equipment.getDualZoneClimate());
        existingEquipment.setOnBoardComputer(equipment.getOnBoardComputer());
        existingEquipment.setPanoramicRoof(equipment.getPanoramicRoof());

        equipmentService.editEquipment(id, existingEquipment);

        for (Automobile automobile : existingEquipment.getAutomobiles()) {
            automobile.setEquipment(existingEquipment);
        }


        return "redirect:/equipment";
    }

    @GetMapping("/add")
    public String addEquipment(){
        return "/equipment/add_equipment";
    }
    @PostMapping("/add")
    public String addEquipment(@ModelAttribute Equipment equipment){
        equipmentService.writeEquipment(equipment);
        return "redirect:/equipment";
    }
}
