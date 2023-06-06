package com.example.week6hw28.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "MyOrder")
public class Order {
    @JsonIgnore
    @Transient
    private final String stat1="new";
    @JsonIgnore
    @Transient
    private final String stat2="inProgress";
    @JsonIgnore
    @Transient
    private final String stat3="completed";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotNull
    @PositiveOrZero
    @Column(nullable = false)
    private Integer quantity;
    @PositiveOrZero
    private double totalPrice;
    @NotNull
    @Column(nullable = false)
    private LocalDate dateReceived;
    @NotEmpty
    @Pattern(regexp = "^("+stat1+"|"+stat2+"|"+stat3+")",message = "status(new-inProgress-completed) only")
    @Column(nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "myUser_id",referencedColumnName = "id")
    @JsonIgnore
    private MyUser myUser;

    @ManyToOne
    @JoinColumn(name = "product_id",referencedColumnName = "id")
    @JsonIgnore
    private Product product;
}
