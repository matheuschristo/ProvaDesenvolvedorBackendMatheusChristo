package com.project.matheuschristo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "pedido")
@AllArgsConstructor
@NoArgsConstructor
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
}
