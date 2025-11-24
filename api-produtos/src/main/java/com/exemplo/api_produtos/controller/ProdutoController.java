package com.exemplo.api_produtos.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import com.exemplo.api_produtos.model.Fornecedor;
import com.exemplo.api_produtos.model.Produto;
import com.exemplo.api_produtos.repository.ProdutoRepository;
import com.exemplo.api_produtos.repository.CategoriaRepository;
import com.exemplo.api_produtos.repository.FornecedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;
    private final FornecedorRepository fornecedorRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Produto> createProduto(@RequestBody Produto produto) {
        if (produto.getCategoria() == null || produto.getCategoria().getId() == null) {
            return ResponseEntity.badRequest().build();
        }

        categoriaRepository.findById(produto.getCategoria().getId())
        .ifPresent(produto::setCategoria);

        if (produto.getFornecedores() != null && !produto.getFornecedores().isEmpty()) {
            produto.getFornecedores().clear();
            produto.getFornecedores().forEach(fornecedor -> {
                fornecedorRepository.findById(fornecedor.getId())
                .ifPresent(produto.getFornecedores()::add);
            });
        }
    Produto savedProduto = produtoRepository.save(produto);
    
    return ResponseEntity.status(HttpStatus.CREATED).body(savedProduto);

    }

    @GetMapping
    public List<Produto> getAllProdutos() {
        return produtoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> getCategoriaById(@PathVariable Long id){
        return produtoRepository.findById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> updateProduto(@PathVariable Long id, @RequestBody Produto produtoDetails){
        return produtoRepository.findById(id).map(produto -> {
            produto.setNome(produtoDetails.getNome());
            Produto updatedProduto = produtoRepository.save(produto);
            return ResponseEntity.ok(updatedProduto);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduto(@PathVariable Long id) {
        if (!produtoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        produtoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

