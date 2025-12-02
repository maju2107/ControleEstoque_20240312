package com.exemplo.api_produtos.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.exemplo.api_produtos.model.Cliente;
import org.springframework.stereotype.Repository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}