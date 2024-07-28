package com.project.matheuschristo.controller;

import com.project.matheuschristo.model.Item;
import com.project.matheuschristo.model.ItemProdutoServico;
import com.project.matheuschristo.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/item")
public class ItemController {

    private final ItemService service;

    public ItemController(ItemService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Item> create(@RequestBody Item item) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createItem(item));
    }

    @PostMapping("vincular/{pedido_id}/{produto_servico_id}/{quantidade}")
    public ResponseEntity<String> vincularItemPedido(@PathVariable("pedido_id") UUID pedidoId, @PathVariable("produto_servico_id") UUID produtoServicoId, @PathVariable("quantidade") int quantidade) throws Exception {
        service.vincularItemPedido(pedidoId, produtoServicoId, quantidade);
        return ResponseEntity.status(HttpStatus.CREATED).body("Item vinculado com sucesso ao pedido.");
    }

    @PutMapping("{id}")
    public ResponseEntity<String> update(@PathVariable("id") UUID id, @RequestBody Item item) throws Exception {
        service.update(id, item);
        return ResponseEntity.status(HttpStatus.OK).body("Item atualizado com sucesso.");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable("id") UUID id) throws Exception {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Item deletado com sucesso.");
    }

}
