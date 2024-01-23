package com.example.db.service.autoMark;

import com.example.db.model.AutomobileMark;
import com.example.db.model.AutomobileModel;
import com.example.db.model.Equipment;

import java.util.List;


public interface AutomobileMarkService {

    public List<AutomobileModel> getAutomobileModelByAutomobileMarkId(Long id);
    public List<Equipment> getEquipmentByAutomobileMarkId(Long id);
    public AutomobileMark writeAutoMark(AutomobileMark automobileMark);
    void editAutoMark(Long id, AutomobileMark automobileMark);
    public String deleteAutoMark(Long id);
    public List<AutomobileMark> getAllAutoMarks();

    public AutomobileMark getAutoMarkById(Long id);
}
