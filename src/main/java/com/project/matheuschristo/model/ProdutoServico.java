package com.project.matheuschristo.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "produto_servico")
public class ProdutoServico {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "preco", nullable = false)
    private BigDecimal preco;

    @Column(name = "desativado")
    private boolean desativado;

    @Column(name = "is_produto", nullable = false)
    private boolean isProduto;

    public ProdutoServico(UUID id, String nome, BigDecimal preco, boolean desativado, boolean isProduto) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.desativado = desativado;
        this.isProduto = isProduto;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public boolean isDesativado() {
        return desativado;
    }

    public void setDesativado(boolean desativado) {
        this.desativado = desativado;
    }

    public boolean isProduto() {
        return isProduto;
    }

    public void setProduto(boolean produto) {
        isProduto = produto;
    }
}
