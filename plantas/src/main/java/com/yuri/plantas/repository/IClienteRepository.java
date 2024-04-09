package com.yuri.plantas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yuri.plantas.model.Cliente;


public interface IClienteRepository extends JpaRepository<Cliente,Integer> {

}
