package com.project.matheuschristo.model;

import java.math.BigDecimal;

public class ItemProdutoServico {

    private int quantidade;

    private String nome;

    private BigDecimal preco;

    private boolean desativado;

    private boolean isProduto;

    public ItemProdutoServico(int quantidade, String nome, BigDecimal preco, boolean desativado, boolean isProduto) {
        this.quantidade = quantidade;
        this.nome = nome;
        this.preco = preco;
        this.desativado = desativado;
        this.isProduto = isProduto;
    }

    public ItemProdutoServico() {}

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
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
