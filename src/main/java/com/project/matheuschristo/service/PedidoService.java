package com.project.matheuschristo.service;

import com.project.matheuschristo.model.Item;
import com.project.matheuschristo.model.Pedido;
import com.project.matheuschristo.model.ProdutoServico;
import com.project.matheuschristo.repository.ItemRepository;
import com.project.matheuschristo.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
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

        if (pedido.getDesconto() != null) {
            for (Item item : pedido.getItens()) {
                if (!item.getProdutoServico().isProduto()) throw new Exception("Item cadastrado é um serviço!");
            }
            if (!pedido.isAberto()) throw new Exception("O produto está fechado.");
        }

        double total = 0;
        for (Item item : pedido.getItens()) {
            total += item.getProdutoServico().getPreco().doubleValue();
        }

        total = total - pedido.getDesconto().doubleValue();

        pedido.setTotal(BigDecimal.valueOf(total));

        repository.save(pedido);

        return pedido;
    }

    public void update(UUID id, Pedido pedido) throws Exception {
        Pedido newPedido = repository.findPedidoById(id).orElseThrow(() -> new Exception("Pedido nao encontrado."));

        newPedido.setAberto(pedido.isAberto());
        if (pedido.getDesconto() != null) {
            for (Item item : pedido.getItens()) {
                if (!item.getProdutoServico().isProduto()) throw new Exception("Item cadastrado é um serviço!");
            }
            if (!pedido.isAberto()) throw new Exception("O produto está fechado.");
            newPedido.setDesconto(pedido.getDesconto());
        }
        double total = 0;
        for (Item item : pedido.getItens()) {
            newPedido.add(item);
            total += item.getProdutoServico().getPreco().doubleValue();
        }
        total = total - newPedido.getDesconto().doubleValue();

        newPedido.setTotal(BigDecimal.valueOf(total));
    }

    public void delete(UUID id) throws Exception {
        Pedido pedido = repository.findPedidoById(id).orElseThrow(() -> new Exception("Pedido nao encontrado."));
        repository.delete(pedido);
    }
}
