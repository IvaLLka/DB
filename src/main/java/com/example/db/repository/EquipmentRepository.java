package com.example.db.repository;

import com.example.db.model.AutomobileMark;
import com.example.db.model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {

    @Query(" SELECT ae FROM AutomobileModel am " +
            "JOIN Automobile a ON am.id = a.automobileModel.id " +
            "JOIN Equipment ae ON a.equipment.id = ae.id " +
            "WHERE am.automobileMark.id = (SELECT id FROM AutomobileMark WHERE id = :id)")
    List<Equipment> findEquipmentByMarkName(Long id);
}