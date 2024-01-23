package com.example.db.repository;

import com.example.db.model.AutomobileModel;
import com.example.db.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("SELECT c FROM Client c WHERE c.id = :Id")
    Client findClientByID(Long Id);
}
