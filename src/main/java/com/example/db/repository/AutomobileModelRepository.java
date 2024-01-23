package com.example.db.repository;

import com.example.db.model.AutomobileModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AutomobileModelRepository extends JpaRepository<AutomobileModel, Long> {

     @Query("SELECT am.name AS model_name, ma.name AS mark_name, a.issueYear, " +
             "s.date AS sale_date, e.bodyColor, e.dualZoneClimate, " +
             "e.electricWindows, e.interiorColor, e.interiorTrim, " +
             "e.onBoardComputer, e.panoramicRoof " +
             "FROM Automobile a " +
             "JOIN a.automobileModel am " +
             "JOIN am.automobileMark ma " +
             "JOIN a.equipment e " +
             "JOIN a.sales s " +
             "WHERE a.issueYear = (SELECT MAX(issueYear) FROM Automobile) " +
             "ORDER BY s.date DESC LIMIT 3")
     List<Object[]> findMarksAndEquipsWithSalesThanTwo();

     @Query("SELECT am FROM AutomobileModel am WHERE am.automobileMark.id = :markId")
     List<AutomobileModel> findModelsByMarkId(Long markId);

     @Query("SELECT a FROM AutomobileModel a WHERE a.automobiles IS EMPTY")
     List<AutomobileModel> findAutomobileModelsWithoutAutomobiles();
}
