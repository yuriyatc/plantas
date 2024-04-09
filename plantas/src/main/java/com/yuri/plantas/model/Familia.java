package com.yuri.plantas.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

@Entity
@Data
@Table
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Familia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "familia",  length = 30)
    private String familia;

    @Column(name = "nombrecientifico",  length = 30)
    private String nombrecientifico;

    @Column(name = "habitat",  length = 15)
    private String habitat;
}

