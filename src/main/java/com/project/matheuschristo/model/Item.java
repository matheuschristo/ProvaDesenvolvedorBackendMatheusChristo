package com.project.matheuschristo.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "item")
public class Item {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "produto_servico_id")
    private ProdutoServico produtoServico;

    @Column(name = "quantidade")
    private int quantidade;

    public Item(UUID id, Pedido pedido, ProdutoServico produtoServico, int quantidade) {
        this.id = id;
        this.pedido = pedido;
        this.produtoServico = produtoServico;
        this.quantidade = quantidade;
    }
}
