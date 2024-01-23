package com.example.db.repository;

import com.example.db.model.Automobile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AutomobileRepository extends JpaRepository<Automobile, Long> {
    @Query("SELECT a FROM Automobile a WHERE a.sales IS EMPTY")
    List<Automobile> findAutomobilesWithoutSales();
}
