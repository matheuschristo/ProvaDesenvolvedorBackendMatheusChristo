package com.project.matheuschristo.service;

import com.project.matheuschristo.model.*;
import com.project.matheuschristo.repository.ItemRepository;
import com.project.matheuschristo.repository.PedidoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
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

    public PedidoSemItem createPedidoSemItem(PedidoSemItem psi) throws Exception {
        Pedido pedido = new Pedido();

        pedido.setTotal(psi.getTotal());
        pedido.setAberto(psi.isAberto());

        if (pedido.isAberto())
            pedido.setDesconto(psi.getDesconto());
        else
            throw new Exception("Não e possivel adicionar desconto a pedidos fechados.");

        repository.save(pedido);

        return psi;
    }

    public PedidoComItem createPedidoComItem(PedidoComItem pci) throws Exception {
        Pedido pedido = new Pedido();

        pedido.setAberto(pci.isAberto());

        double totalProdutos = 0;
        double totalServicos = 0;
        for (UUID id : pci.getItensId()) {
            Item item = itemRepository.findItemById(id).orElseThrow(() -> new Exception("Item não encontrado."));

            if (item.getProdutoServico().isDesativado()) throw new Exception("Produto/Serviço esta desativado.");

            if (item.getProdutoServico().isProduto())
                totalProdutos += item.getProdutoServico().getPreco().doubleValue();
            else
                totalServicos += item.getProdutoServico().getPreco().doubleValue();

            pedido.add(item);
        }

        if (pci.getDesconto() != null || pci.getDesconto().doubleValue() > 0) {
           if (pedido.isAberto())
                pedido.setDesconto(pci.getDesconto());
            else
                throw new Exception("Não e possivel adicionar desconto a pedidos fechados.");

            pedido.setDesconto(pci.getDesconto());
            totalProdutos = totalProdutos - pedido.getDesconto().doubleValue();
        }

        pedido.setTotal(BigDecimal.valueOf(totalProdutos + totalServicos));

        repository.save(pedido);

        // Salvar pedido nos itens
        for (Item item : pedido.getItens()) {
            item.setPedido(pedido);
            itemRepository.save(item);
        }

        return pci;
    }

    public void update(UUID id, PedidoSemItem pedido) throws Exception {
        Pedido newPedido = repository.findPedidoById(id).orElseThrow(() -> new Exception("Pedido nao encontrado."));

        newPedido.setAberto(pedido.isAberto());
        if (pedido.getDesconto() != null && !pedido.isAberto()) throw new Exception("O pedido está fechado.");

        double totalProdutos = 0;
        double totalServicos = 0;
        for (Item item : newPedido.getItens()) {
            if (item.getProdutoServico().isProduto())
                totalProdutos += item.getProdutoServico().getPreco().doubleValue();
            else
                totalServicos += item.getProdutoServico().getPreco().doubleValue();

        }

        if (totalProdutos > 0) totalProdutos = totalProdutos - newPedido.getDesconto().doubleValue();

        newPedido.setTotal(BigDecimal.valueOf(totalProdutos + totalServicos));

        repository.save(newPedido);
    }

    public void delete(UUID id) throws Exception {
        Pedido pedido = repository.findPedidoById(id).orElseThrow(() -> new Exception("Pedido nao encontrado."));

        if (!pedido.getItens().isEmpty()) throw new Exception("Pedido contem itens!");

        repository.delete(pedido);
    }

    public List<Pedido> getPedidos() {
        return repository.getPedidos();
    }

    public Pedido buscarPedidos(Integer pageSize, Integer pageIndex) throws Exception {
        Integer firstResult = pageSize * pageIndex;
        return repository.buscarPedidos(pageSize, firstResult).orElseThrow(() -> new Exception("Pedido nao encontrado."));
    }

    public List<ItemProdutoServico> buscarItensPedido(UUID pedidoId, Integer pageSize, Integer pageIndex) throws Exception {

        Integer firstResult = pageSize * pageIndex;
        List<Item> itens = itemRepository.findItemByPedidoId(pedidoId, pageSize, firstResult).orElseThrow(() -> new Exception("Não foram encontrados itens."));

        List<ItemProdutoServico> itemProdutoServicos = new ArrayList<>();
        for (Item item : itens) {
            itemProdutoServicos.add(item.toItemProdutoServico(item));
        }

        return itemProdutoServicos;
    }
}
