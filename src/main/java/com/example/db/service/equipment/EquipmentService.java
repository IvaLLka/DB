package com.example.db.service.equipment;

import com.example.db.model.AutomobileModel;
import com.example.db.model.Equipment;

import java.util.List;

public interface EquipmentService {
    public Equipment writeEquipment(Equipment equipment);
    void editEquipment(Long id, Equipment equipment);
    public String deleteEquipment(Long id);
    public List<Equipment> getAllEquipment();

    public Equipment getEquipmentById(Long id);
}
