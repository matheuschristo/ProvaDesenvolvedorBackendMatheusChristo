package com.project.matheuschristo.controller;

import com.project.matheuschristo.model.Pedido;
import com.project.matheuschristo.repository.PedidoRepository;
import com.project.matheuschristo.service.PedidoService;
import org.springframework.boot.rsocket.context.RSocketPortInfoApplicationContextInitializer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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

    @PutMapping("{id}")
    public ResponseEntity<String> update(@PathVariable("id")UUID id, @RequestBody Pedido pedido) throws Exception {
        service.update(id, pedido);
        return ResponseEntity.status(HttpStatus.OK).body("Pedido atualizado com sucesso.");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable("id") UUID id) throws Exception {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Pedido deletado com sucesso.");
    }
}
