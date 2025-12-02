package com.exemplo.api_produtos.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.http.*;
import lombok.RequiredArgsConstructor;
import com.exemplo.api_produtos.model.Venda;
import com.exemplo.api_produtos.repository.VendaRepository;
import com.exemplo.api_produtos.services.VendaService;


@RestController
@RequestMapping("/vendas")
@RequiredArgsConstructor
public class VendaController {

    private final VendaRepository vendaRepository;
    private final VendaService vendaService;
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createVenda(@RequestBody Venda venda) {
        try {
            Venda savedVenda = vendaService.verificarVenda(venda);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedVenda);
        } catch (RuntimeException e) {
           return (ResponseEntity<?>) ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public List<Venda> getAllVendas() {
        return vendaRepository.findAll();
    
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venda> getVendaById(@PathVariable Long id){
        return vendaRepository.findById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }
    
}
