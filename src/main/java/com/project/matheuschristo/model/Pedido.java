package com.project.matheuschristo.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "total")
    private BigDecimal total;

    @Column(name = "desconto")
    private BigDecimal desconto;

    @Column(name = "is_aberto")
    private boolean isAberto;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<Item> itens;

    public Pedido(UUID id, BigDecimal total, BigDecimal desconto, boolean isAberto) {
        this.id = id;
        this.total = total;
        this.desconto = desconto;
        this.isAberto = isAberto;
    }

    public void add(Item item) {
        itens.add(item);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public void setDesconto(BigDecimal desconto) {
        this.desconto = desconto;
    }

    public boolean isAberto() {
        return isAberto;
    }

    public void setAberto(boolean aberto) {
        isAberto = aberto;
    }

    public List<Item> getItens() {
        return itens;
    }
}
