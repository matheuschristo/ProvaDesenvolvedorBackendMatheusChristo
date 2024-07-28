package com.project.matheuschristo.controller;

import com.project.matheuschristo.model.ProdutoServico;
import com.project.matheuschristo.service.ProdutoServicoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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

    @PutMapping("{id}")
    public ResponseEntity<String> update(@PathVariable("id") UUID id, @RequestBody ProdutoServico produtoServico) throws Exception {
        service.update(id, produtoServico);
        return ResponseEntity.status(HttpStatus.OK).body("Produto/Servico atualizado com sucesso.");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable("id") UUID id) throws Exception {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Produto/Serviço excluido com sucesso!");
    }

    @GetMapping("buscar")
    public ResponseEntity<?> buscarProdutosServicos(
            @RequestParam(name = "page_size", required = false) Integer pageSize,
            @RequestParam(name = "page_index", required = false) Integer pageIndex
    ) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(service.buscarProdutoServico(pageSize, pageIndex));
    }
}
