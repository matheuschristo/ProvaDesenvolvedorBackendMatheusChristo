package com.project.matheuschristo.controller;

import com.project.matheuschristo.model.Pedido;
import com.project.matheuschristo.repository.PedidoRepository;
import com.project.matheuschristo.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pedido")
public class PedidoController {

    private final PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Pedido> create(@RequestBody Pedido pedido) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(pedido));
    }
}
