package com.yuri.plantas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yuri.plantas.model.Planta;

public interface PlantasRepository extends JpaRepository<Planta,Integer> {

}
