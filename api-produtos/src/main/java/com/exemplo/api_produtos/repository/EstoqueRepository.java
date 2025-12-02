package com.exemplo.api_produtos.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.exemplo.api_produtos.model.Estoque;
import org.springframework.stereotype.Repository;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {

}
