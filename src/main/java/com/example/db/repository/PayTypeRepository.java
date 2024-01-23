package com.example.db.repository;

import com.example.db.model.PayType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayTypeRepository extends JpaRepository<PayType, Long> {
}
