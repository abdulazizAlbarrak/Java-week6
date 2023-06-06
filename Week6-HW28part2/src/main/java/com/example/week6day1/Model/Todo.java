package com.example.week6day1.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Todo {
    @Id
    private Integer id;
    private String mssg;

    @OneToOne
    @MapsId
    @JsonIgnore
    private Myuser myuser;
}
