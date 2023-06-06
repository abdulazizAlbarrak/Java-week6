package com.example.week6hw28.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotEmpty
    @Column(nullable = false)
    private String name;
    @NotNull
    @Positive
    @Column(nullable = false)
    private double price;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "product")
    private Set<Order> orders;
}
