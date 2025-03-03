package com.project.matheuschristo.controller;

import com.project.matheuschristo.model.Pedido;
import com.project.matheuschristo.model.PedidoComItem;
import com.project.matheuschristo.model.PedidoSemItem;
import com.project.matheuschristo.repository.PedidoRepository;
import com.project.matheuschristo.service.PedidoService;
import org.apache.coyote.Response;
import org.springframework.boot.rsocket.context.RSocketPortInfoApplicationContextInitializer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/pedido")
public class PedidoController {

    private final PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @PostMapping("create_pedido_sem_itens")
    public ResponseEntity<PedidoSemItem> createPedidoSemItem(@RequestBody PedidoSemItem psi) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createPedidoSemItem(psi));
    }

    @PutMapping("{id}")
    public ResponseEntity<String> update(@PathVariable("id")UUID id, @RequestBody PedidoSemItem pedido) throws Exception {
        service.update(id, pedido);
        return ResponseEntity.status(HttpStatus.OK).body("Pedido atualizado com sucesso.");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable("id") UUID id) throws Exception {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Pedido deletado com sucesso.");
    }

    @GetMapping("buscar")
    public ResponseEntity<?> buscarPedidos(
            @RequestParam(name = "page_size", required = false) Integer pageSize,
            @RequestParam(name = "page_index", required = false) Integer pageIndex
    ) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(service.buscarPedidos(pageSize, pageIndex));
    }

    @GetMapping("item_pedido/{pedidoId}")
    public ResponseEntity<?> buscarItensPedidos(
            @RequestParam(name = "page_size", required = false) Integer pageSize,
            @RequestParam(name = "page_index", required = false) Integer pageIndex,
            @PathVariable("pedidoId") UUID pedidoId
    ) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(service.buscarItensPedido(pedidoId, pageSize, pageIndex));
    }
}
