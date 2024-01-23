package com.example.db.service.autoMark;

import com.example.db.model.AutomobileMark;
import com.example.db.model.AutomobileModel;
import com.example.db.model.Equipment;
import com.example.db.repository.AutomobileMarkRepository;
import com.example.db.repository.AutomobileModelRepository;
import com.example.db.repository.EquipmentRepository;
import com.example.db.service.auto.AutomobileServiceImpl;
import com.example.db.service.autoModel.AutomobileModelService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class AutomobileMarkServiceImpl implements AutomobileMarkService {

    @Autowired
    AutomobileServiceImpl automobileServiceImpl;

    @Autowired
    private final AutomobileMarkRepository automobileMarkRepository;

    private final EquipmentRepository equipmentRepository;

    private final AutomobileModelRepository automobileModelRepository;


    public AutomobileMarkServiceImpl(AutomobileMarkRepository automobileMarkRepository,
                                     EquipmentRepository equipmentRepository,
                                     AutomobileModelRepository automobileModelRepository) {
        this.automobileMarkRepository = automobileMarkRepository;
        this.equipmentRepository = equipmentRepository;
        this.automobileModelRepository = automobileModelRepository;
    }

    @Override
    public List<AutomobileModel> getAutomobileModelByAutomobileMarkId(Long id) {
        return automobileModelRepository.findModelsByMarkId(id);
    }

    @Override
    public List<Equipment> getEquipmentByAutomobileMarkId(Long id) {
        return equipmentRepository.findEquipmentByMarkName(id);
    }

    @Override
    public AutomobileMark writeAutoMark(AutomobileMark automobileMark) {
        return automobileMarkRepository.save(automobileMark);
    }


    @Override
    public void editAutoMark(Long id, AutomobileMark automobileMark) {
        AutomobileMark newAutomobileMark = automobileMarkRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("AutomobileMark not found with ID:" + id));

        newAutomobileMark.setName(automobileMark.getName());
        newAutomobileMark.setAutomobileModels(automobileMark.getAutomobileModels());
        automobileMarkRepository.save(newAutomobileMark);

    }

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public String deleteAutoMark(Long id) {
        automobileMarkRepository.deleteById(id);
        return null;
    }

    @Override
    public List<AutomobileMark> getAllAutoMarks() {
        return automobileMarkRepository.findAll();
    }

    @Override
    public AutomobileMark getAutoMarkById(Long id) {
        return automobileMarkRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("AutomobileMark not found with ID:" + id));
    }
}
