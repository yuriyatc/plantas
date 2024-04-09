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

import com.yuri.plantas.repository.IClienteRepository;
import com.yuri.plantas.model.Cliente;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class ClienteController {
    @Autowired
    private IClienteRepository clienteRepository;

    @GetMapping("")
    public String inicio() {
        return "mira hermosa si funcionando";
    }

    @GetMapping("/Cliente")
    public List<Cliente> getCliente() {
        return clienteRepository.findAll();
    }

    @GetMapping("/Cliente/{id}")
    public ResponseEntity<Object> getClienteById(@PathVariable("id") Integer id) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        if (clienteOptional.isPresent()) {
            return new ResponseEntity<Object>(clienteOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<Object>("No se encontró el cliente con el id: " + id, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/modificarCLiente/{id}")
    public ResponseEntity<String> modificarCliente(
            @PathVariable("id") Integer id,
            @RequestBody Cliente clienteDetails) {
        Cliente cliente = clienteRepository.findById(id).orElse(null);
        if (cliente != null) {
            cliente.setId(clienteDetails.getId());
            cliente.setNombre(clienteDetails.getNombre());
            cliente.setCorreo(clienteDetails.getCorreo());
            cliente.setTelefono(clienteDetails.getTelefono());
            clienteRepository.save(cliente);
            return new ResponseEntity<>("se modifico el cliente con id: " + id, HttpStatus.NOT_FOUND);

        } else {
            return new ResponseEntity<>("No se encontró el cliente con id: " + id, HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/guardarClientes")
    public ResponseEntity<Object> guardarCliente(@RequestBody Cliente cliente) {
        Optional<Cliente> clienteOpcional = clienteRepository.findById(cliente.getId());
        HashMap<String, Object> datos = new HashMap<>();
        if (clienteOpcional.isPresent()) {
            datos.put("error", true);
            datos.put("mensaje", "El ID ya está registrado con otro cliente");
            return new ResponseEntity<>(datos, HttpStatus.CONFLICT);
        }

        clienteRepository.save(cliente);
        datos.put("data", cliente);
        datos.put("mensaje", "cliente creado");

        return new ResponseEntity<>(datos, HttpStatus.CREATED);

    }

    @DeleteMapping("/eliminarCliente/{id}")
    public ResponseEntity<String> eliminarCliente(@PathVariable("id") Integer id) {
        Cliente cliente = clienteRepository.findById(id).orElse(null);
        if (cliente != null) {
            clienteRepository.delete(cliente);
            return new ResponseEntity<>("cliente eliminado: " + id, HttpStatus.OK);

        } else {

            return new ResponseEntity<>("No se encontró el cliente con ID: " + id, HttpStatus.NOT_FOUND);
        }
    }

}