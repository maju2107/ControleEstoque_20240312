package com.exemplo.api_produtos.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.exemplo.api_produtos.model.Categoria;
import org.springframework.stereotype.Repository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    List<Categoria> findByNome(String nome);
    List<Categoria> findByNomeContaining(String parteDoNome);
    public Long findById();
}