package com.exemplo.api_produtos.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;

import com.exemplo.api_produtos.model.Cliente;
import com.exemplo.api_produtos.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteRepository clienteRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) {
        if (cliente.getNome() == null || cliente.getEmail() == null) {
            return ResponseEntity.badRequest().build();
        }
        
    Cliente savedCliente = clienteRepository.save(cliente);
    
    return ResponseEntity.status(HttpStatus.CREATED).body(savedCliente);

    }

    @GetMapping
    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Long id){
        return clienteRepository.findById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable Long id, @RequestBody Cliente clienteDetails){
        return clienteRepository.findById(id).map(cliente -> {
            cliente.setNome(clienteDetails.getNome());
            cliente.setEmail(clienteDetails.getEmail());
            Cliente updatedCliente = clienteRepository.save(cliente);
            return ResponseEntity.ok(updatedCliente);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduto(@PathVariable Long id) {
        if (!clienteRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        clienteRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
}
