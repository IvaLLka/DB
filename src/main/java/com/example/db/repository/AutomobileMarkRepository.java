package com.example.db.repository;

import com.example.db.model.Automobile;
import com.example.db.model.AutomobileMark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AutomobileMarkRepository extends JpaRepository<AutomobileMark, Long> {


}

