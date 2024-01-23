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
@Table(name= "pay_type")
public class PayType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( nullable = false)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "payType", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Sale> sales = new ArrayList<>();
}
