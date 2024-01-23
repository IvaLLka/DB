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
@Table(name = "automobile_model")
public class AutomobileModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "id_mark_auto", nullable = false)
    private AutomobileMark automobileMark;

    @JsonIgnore
    @OneToMany(mappedBy = "automobileModel", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Automobile> automobiles = new ArrayList<>();

}
