package com.project.matheuschristo.controller;

import com.project.matheuschristo.model.ProdutoServico;
import com.project.matheuschristo.service.ProdutoServicoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/produto_servico")
public class ProdutoServicoController {

    private final ProdutoServicoService service;

    public ProdutoServicoController(ProdutoServicoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ProdutoServico> create(@RequestBody ProdutoServico produtoServico) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(produtoServico));
    }
}
