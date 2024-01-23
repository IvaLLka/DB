package com.example.db.service.equipment;

import com.example.db.model.Equipment;
import com.example.db.repository.EquipmentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentServiceImpl implements EquipmentService {

    @Autowired
    private final EquipmentRepository equipmentRepository;

    public EquipmentServiceImpl(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    @Override
    public Equipment writeEquipment(Equipment equipment) {
        return equipmentRepository.save(equipment);
    }

    @Override
    public void editEquipment(Long id, Equipment equipment) {
        Equipment newEquipment = equipmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Equipment not found with ID:" + id));
        newEquipment.setAutomobiles(equipment.getAutomobiles());
        newEquipment.setBodyColor(equipment.getBodyColor());
        newEquipment.setInteriorColor(equipment.getInteriorColor());
        newEquipment.setElectricWindows(equipment.getElectricWindows());
        newEquipment.setInteriorTrim(equipment.getInteriorTrim());
        newEquipment.setDualZoneClimate(equipment.getDualZoneClimate());
        newEquipment.setOnBoardComputer(equipment.getOnBoardComputer());
        newEquipment.setPanoramicRoof(equipment.getPanoramicRoof());
        equipmentRepository.save(newEquipment);
    }

    @Override
    public String deleteEquipment(Long id) {
        equipmentRepository.deleteById(id);
        return null;
    }

    @Override
    public List<Equipment> getAllEquipment() {
        return equipmentRepository.findAll();
    }

    @Override
    public Equipment getEquipmentById(Long id) {
        return equipmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Equipment not found with ID:" + id));
    }

}
