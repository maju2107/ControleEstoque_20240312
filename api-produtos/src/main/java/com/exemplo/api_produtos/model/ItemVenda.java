package com.exemplo.api_produtos.model;

import java.math.BigDecimal;
import jakarta.persistence.*;

@Entity
@Table (name = "tb_itemVenda")
public class ItemVenda {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "venda_id", nullable = false)
    private Venda venda;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    private Integer quantidade;

    private BigDecimal precoUnitario;

    public Long  getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public Integer getQuantidade() {return quantidade;}
    public void setQuantidade(Integer quantidade) {this.quantidade = quantidade;}
    public Produto getProduto() {return produto;}
    public void setProduto (Produto produto) {this.produto = produto;} 
    public Venda getVenda() {return venda;}
    public void setVenda(Venda venda) {this.venda = venda;} 
    public BigDecimal getPrecoUnitario() {return precoUnitario;}
    public void setPrecoUnitario(BigDecimal precoUnitario) {this.precoUnitario = precoUnitario;}

    
}
