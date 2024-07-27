package com.project.matheuschristo.service;

import com.project.matheuschristo.model.*;
import com.project.matheuschristo.repository.ItemRepository;
import com.project.matheuschristo.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.UUID;

@Service
public class PedidoService {

    private final PedidoRepository repository;
    private final ItemRepository itemRepository;

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

        double total = 0;
        for (Item item : pedido.getItens()) {
            total += item.getProdutoServico().getPreco().doubleValue();
        }

        total = total - pedido.getDesconto().doubleValue();

        pedido.setTotal(BigDecimal.valueOf(total));

        repository.save(pedido);

        return pedido;
    }

    public PedidoSemItem createPedidoSemItem(PedidoSemItem psi) throws Exception {
        Pedido pedido = new Pedido();

        pedido.setTotal(psi.getTotal());
        pedido.setAberto(psi.isAberto());

        if (pedido.isAberto())
            pedido.setDesconto(psi.getDesconto());
        else
            throw new Exception("Não e possivel adicionar desconto a pedidos fechados.");

        return psi;
    }

    public PedidoComItem createPedidoComItem(PedidoComItem pci) throws Exception {
        Pedido pedido = new Pedido();

        pedido.setAberto(pci.isAberto());

        double total = 0;
        for (UUID id : pci.getItensId()) {
            Item item = itemRepository.findItemById(id).orElseThrow(() -> new Exception("Item não encontrado."));

            if (item.getProdutoServico().isDesativado()) throw new Exception("Produto/Serviço esta desativado.");

            total += item.getProdutoServico().getPreco().doubleValue();
            pedido.add(item);
        }

        if (pci.getDesconto() != null || pci.getDesconto().doubleValue() > 0) {
            for (Item item : pedido.getItens()) {
                if (!item.getProdutoServico().isProduto()) throw new Exception("Não e possivel adicionar desconto a um serviço.");
            }

            if (pedido.isAberto())
                pedido.setDesconto(pci.getDesconto());
            else
                throw new Exception("Não e possivel adicionar desconto a pedidos fechados.");

            pedido.setDesconto(pci.getDesconto());
            total = total - pedido.getDesconto().doubleValue();
        }

        pedido.setTotal(BigDecimal.valueOf(total));

        repository.save(pedido);

        // Salvar pedido nos itens
        for (Item item : pedido.getItens()) {
            item.setPedido(pedido);
            itemRepository.save(item);
        }

        return pci;
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

    public List<Pedido> getPedidos() {
        return repository.getPedidos();
    }
}
