package com.example.db.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "on-board_computer", nullable = false)
    private Boolean onBoardComputer;

    @Column(name = "electric_windows", nullable = false)
    private Boolean  electricWindows;

    @Column(name = " dual zone_climate", nullable = false)
    private Boolean  dualZoneClimate;

    @Column(name = "interior_trim", nullable = false)
    private String  interiorTrim;

    @Column(name = "panoramic_roof", nullable = false)
    private Boolean  panoramicRoof;

    @Column(name = " interior_color", nullable = false)
    private String  interiorColor;

    @Column(name = "body_color", nullable = false)
    private String  bodyColor;

    @JsonIgnore
    @OneToMany(mappedBy = "equipment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Automobile> automobiles = new ArrayList<>();
}
