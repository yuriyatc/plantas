package com.yuri.plantas.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yuri.plantas.repository.FamiliaRepository;
import com.yuri.plantas.repository.IClienteRepository;
import com.yuri.plantas.model.Cliente;
import com.yuri.plantas.model.Familia;

import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class FamiliaController {

    @Autowired
    private FamiliaRepository familiaRepository;

    @GetMapping("inicioFamilia")
    public String inicioFamilia() {
        return "funciona";
    }

    @GetMapping("/familia")
    public List<Familia> getCliente() {
        return familiaRepository.findAll();
    }

    @GetMapping("/familia/{id}")
    public ResponseEntity<Object> getClienteById(@PathVariable("id") Integer id) {
        Optional<Familia> familiOptional = familiaRepository.findById(id);
        if (familiOptional.isPresent()) {
            return new ResponseEntity<Object>(familiOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<Object>("No se encontró la familia con el id: " + id, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/modificarFamilia/{id}")
    public ResponseEntity<String> modificarCliente(
            @PathVariable("id") Integer id,
            @RequestBody Familia familiaDetails) {
        Familia familia = familiaRepository.findById(id).orElse(null);
        if (familia != null) {
            familia.setId(familiaDetails.getId());
            familia.setFamilia(familiaDetails.getFamilia());
            familia.setHabitat(familiaDetails.getHabitat());
            familia.setNombrecientifico(familiaDetails.getNombrecientifico());
            familiaRepository.save(familia);       
            return new ResponseEntity<>("se modifico la familia con id: " + id, HttpStatus.OK);

        } else {
            return new ResponseEntity<>("No se encontró la familia con id: " + id, HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/guardarfamilia")
    public ResponseEntity<Object> guardarFamilia(@RequestBody Familia familia) {
        Optional<Familia> familiOptional = familiaRepository.findById(familia.getId());
        HashMap<String, Object> datos = new HashMap<>();
        if (familiOptional.isPresent()) {
            datos.put("error", true);
            datos.put("mensaje", "El ID ya está registrado con otra familia");
            return new ResponseEntity<>(datos, HttpStatus.CONFLICT);
        }

        familiaRepository.save(familia);
        datos.put("data", familia);
        datos.put("mensaje", "familia creada");

        return new ResponseEntity<>(datos, HttpStatus.CREATED);

    }

    @DeleteMapping("/eliminarFamilia/{id}")
    public ResponseEntity<String> eliminarfamilia(@PathVariable("id") Integer id) {
        Familia familia = familiaRepository.findById(id).orElse(null);
        if (familia != null) {
            familiaRepository.delete(familia);
            return new ResponseEntity<>("familia eliminada: " + id, HttpStatus.OK);

        } else {

            return new ResponseEntity<>("No se encontró la familia con ID: " + id, HttpStatus.NOT_FOUND);
        }
    }


}
