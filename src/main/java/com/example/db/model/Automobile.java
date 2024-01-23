package com.example.db.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Automobile {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String win;
    @Column(name = "issue_year", nullable = false)
    private String issueYear;
    @Column(name = "engine_capacity", nullable = false)
    private Double engineCapacity;
    @Column(nullable = false)
    private Integer power;

    @JsonIgnore
    @OneToMany( mappedBy = "automobile", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Sale> sales;

    @ManyToOne
    @JoinColumn(name = "id_model_auto", nullable = false)
    private AutomobileModel automobileModel;


    @ManyToOne
    @JoinColumn(name = "id_equipment", nullable = false)
    private Equipment equipment;









}
