package com.exemplo.api_produtos.model;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_vendas")
public class Venda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal valorTotal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemVenda> itens;

    public BigDecimal getTotal() {return valorTotal;}
    public void setValorTotal(BigDecimal valorTotal) {this.valorTotal = valorTotal;}
    public Cliente getCliente() {return cliente;}
    public void setCliente(Cliente cliente) {this.cliente = cliente;}
    public List<ItemVenda> getItens() {return itens;}
    public void setItemVenda (List<ItemVenda> itens) {this.itens = itens;} 
}