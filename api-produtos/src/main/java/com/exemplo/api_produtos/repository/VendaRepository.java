package com.exemplo.api_produtos.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.exemplo.api_produtos.model.Venda;
import org.springframework.stereotype.Repository;

public interface VendaRepository extends JpaRepository<Venda, Long> {

}

