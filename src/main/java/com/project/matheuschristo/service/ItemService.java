package com.project.matheuschristo.service;

import com.project.matheuschristo.model.Item;
import com.project.matheuschristo.model.ItemProdutoServico;
import com.project.matheuschristo.model.Pedido;
import com.project.matheuschristo.model.ProdutoServico;
import com.project.matheuschristo.repository.ItemRepository;
import com.project.matheuschristo.repository.PedidoRepository;
import com.project.matheuschristo.repository.ProdutoServicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ItemService {

    private final ItemRepository repository;
    private final ProdutoServicoRepository produtoServicoRepository;
    private final PedidoRepository pedidoRepository;

    public ItemService(ItemRepository repository, ProdutoServicoRepository produtoServicoRepository, PedidoRepository pedidoRepository) {
        this.repository = repository;
        this.produtoServicoRepository = produtoServicoRepository;
        this.pedidoRepository = pedidoRepository;
    }

    public Item createItem(Item item) {
        repository.save(item);

        return item;
    }

    public ItemProdutoServico createItemProdutoServico(ItemProdutoServico ipc) {

        ProdutoServico produtoServico = new ProdutoServico();
        produtoServico.setNome(ipc.getNome());
        produtoServico.setPreco(ipc.getPreco());
        produtoServico.setDesativado(ipc.isDesativado());
        produtoServico.setProduto(ipc.isProduto());
        produtoServicoRepository.save(produtoServico);

        Item item = new Item();
        item.setProdutoServico(produtoServico);
        item.setQuantidade(ipc.getQuantidade());
        repository.save(item);

        return ipc;
    }

    public void vincularItemPedido(UUID pedidoId, UUID itemId) throws Exception {
        Pedido pedido = pedidoRepository.findPedidoById(pedidoId).orElseThrow(() -> new Exception("Pedido não encontrado."));

        Item item = repository.findItemById(itemId).orElseThrow(() -> new Exception("Item não encontrado."));

        item.setPedido(pedido);

        repository.save(item);
        pedidoRepository.save(pedido);
    }

    public void update(UUID id, Item item) throws Exception {
        Item newItem = repository.findItemById(id).orElseThrow(() -> new Exception("Item não encontrado."));;
        newItem.setQuantidade(item.getQuantidade());
        newItem.setPedido(item.getPedido());
        newItem.setProdutoServico(item.getProdutoServico());
    }

    public void delete(UUID id) throws Exception {
        Item item = repository.findItemById(id).orElseThrow(() -> new Exception("Item não encontrado."));;
        repository.delete(item);
    }

    public List<Item> getItens() {
        return repository.getItens();
    }

}
