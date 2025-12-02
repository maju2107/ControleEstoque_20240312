package com.exemplo.api_produtos.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

import com.exemplo.api_produtos.model.Venda;
import com.exemplo.api_produtos.model.ItemVenda;
import com.exemplo.api_produtos.model.Produto;
import com.exemplo.api_produtos.repository.VendaRepository;
import com.exemplo.api_produtos.repository.ProdutoRepository;

@Service // indica que a classe contémlógica de negócio e pode injetarr o serviço desenvolvido em outra classe
@RequiredArgsConstructor
public class VendaService{
    private final VendaRepository vendaRepository;
    private final ProdutoRepository produtoRepository;

    @Transactional
    public Venda verificarVenda (Venda venda) {
        for (ItemVenda item : venda.getItemVenda() ) {
            Produto produto = produtoRepository.findById(item.getProduto().getId()) 
                .orElseThrow(()-> new RuntimeException("Produto não encontrado" + item.getProduto().getId()));

            if (produto.getEstoque().getQuantidade() < item.getQuantidade()) {
                throw new RuntimeException("Estoque insuficiente para fazer a venda do produto :" + produto.getNome());
            }

            produto.getEstoque().setQuantidade(produto.getEstoque().getQuantidade() - item.getQuantidade());
            produtoRepository.save(produto);

            item.setVenda(venda);
        }
         return vendaRepository.save(venda);
    }

}
