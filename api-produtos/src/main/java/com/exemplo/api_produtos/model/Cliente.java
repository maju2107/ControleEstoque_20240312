package com.exemplo.api_produtos.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "tb_clientes")

public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String nome;
    private String email;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Venda> vendas;

    public Cliente() {}

    public Cliente(String nome, String email, List<Venda> vendas) {
        this.nome = nome;
        this.email = email;
        this.vendas = vendas;
    }

    public String  getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
    public List<Venda> getVendas() {return vendas;}
    public void setVendas (List<Venda> vendas) {this.vendas = vendas;} 
}