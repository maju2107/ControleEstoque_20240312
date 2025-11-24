package com.exemplo.api_produtos.controller;

import java.util.List;

import com.exemplo.api_produtos.repository.CategoriaRepository;
import com.exemplo.api_produtos.model.Categoria;
import lombok.RequiredArgsConstructot;
import org.springframework.htpp.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/categorias")
@RequiredAgsConstructor
public class CategoriaController {

    private final CategoriaRepository categoriarepository;

    @PostMapping
    public Produto criar(@RequestBody Produto produto) {
        return repository.save(produto);
    }

    @GetMapping
    public List<Categoria> getAllCategoria() {
        return categoriarepository.findAll();
    }

    @GetMapping("/{id}")
    public Produto buscar(@PathVariable Long id){
        return repository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Produto atualizar(@PathVariable Long id, @RequestBody Produto novoProduto){
        return repository.findById(id).map(produto -> {
            produto.setNome(novoProduto.getNome());
            produto.setPreco(novoProduto.getPreco());
            return repository.save(produto);
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        repository.deleteById(id);
    }
}



