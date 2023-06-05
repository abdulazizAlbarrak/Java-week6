package com.example.week6hw27.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotEmpty(message = "title should not be empty")
    @Column(columnDefinition = "varchar(30) not null")
    private String title;
    @NotEmpty(message = "body should not be empty")
    @Column(columnDefinition = "varchar(800) not null")
    @Size(min = 40,message = "body should have at least 40 letters")
    private String body;


    @ManyToOne
    @JoinColumn(name = "myUser_id",referencedColumnName = "id")
    @JsonIgnore
    private MyUser myUser;
}
