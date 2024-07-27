package com.project.matheuschristo.service;

import com.project.matheuschristo.model.Item;
import com.project.matheuschristo.model.Pedido;
import com.project.matheuschristo.repository.ItemRepository;
import com.project.matheuschristo.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class PedidoService {

    private PedidoRepository repository;
    private ItemRepository itemRepository;

    public PedidoService(PedidoRepository repository, ItemRepository itemRepository) {
        this.repository = repository;
        this.itemRepository = itemRepository;
    }

    public Pedido create(Pedido pedido) throws Exception {

        if (pedido.getDesconto() != null || pedido.getDesconto().doubleValue() > 0) {
            for (Item item : pedido.getItens()) {
                if (!item.getProdutoServico().isProduto()) throw new Exception("Item cadastrado é um serviço!");
            }
            if (!pedido.isAberto()) throw new Exception("O produto está fechado.");
        }

        repository.save(pedido);

        return pedido;
    }
}
