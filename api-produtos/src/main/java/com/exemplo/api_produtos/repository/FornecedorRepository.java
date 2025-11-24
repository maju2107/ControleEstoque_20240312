package com.exemplo.api_produtos.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.exemplo.api_produtos.model.Fornecedor;
import org.springframework.stereotype.Repository;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
    List<Fornecedor> findByNome(String nome);
    List<Fornecedor> findByNomeContaining(String parteDoNome);
    public Long findById();
    
}
