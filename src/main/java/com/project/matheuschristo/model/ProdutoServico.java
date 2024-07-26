package com.project.matheuschristo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Entity
@Table(name = "produto_servico")
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoServico {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "preco", nullable = false)
    private BigDecimal preco;

    @Column(name = "desativado", nullable = false)
    private boolean desativado;

    @Column(name = "is_produto", nullable = false)
    private boolean isProduto;
}
