package com.project.matheuschristo.service;

import com.project.matheuschristo.model.Item;
import com.project.matheuschristo.model.ItemProdutoServico;
import com.project.matheuschristo.model.Pedido;
import com.project.matheuschristo.model.ProdutoServico;
import com.project.matheuschristo.repository.ItemRepository;
import com.project.matheuschristo.repository.PedidoRepository;
import com.project.matheuschristo.repository.ProdutoServicoRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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

    public void vincularItemPedido(UUID pedidoId, UUID produtoServicoId, int quantidade) throws Exception {
        Pedido pedido = pedidoRepository.findPedidoById(pedidoId).orElseThrow(() -> new Exception("Pedido não encontrado."));
        ProdutoServico produtoServico = produtoServicoRepository.findProdutoServicoById(produtoServicoId).orElseThrow(() -> new Exception("Produto/Servico não encontrado."));

        if (produtoServico.isDesativado()) throw new Exception("Produto/Serviço esta desativado.");

        if (!produtoServico.isProduto() && pedido.getDesconto().doubleValue() > 0) throw new Exception("Não e possivel adionar um Serviço a um pedido com desconto.");

        Item item = new Item();
        item.setProdutoServico(produtoServico);
        item.setQuantidade(quantidade);

        if (pedido.getTotal() != null)
            pedido.setTotal(BigDecimal.valueOf(pedido.getTotal().doubleValue() + item.getProdutoServico().getPreco().toBigInteger().doubleValue()));
        else
            pedido.setTotal(BigDecimal.valueOf(item.getProdutoServico().getPreco().toBigInteger().doubleValue() - pedido.getDesconto().doubleValue()));

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
        Item item = repository.findItemById(id).orElseThrow(() -> new Exception("Item não encontrado."));

        item.setPedido(null);
        item.setProdutoServico(null);

        repository.save(item);
        repository.delete(item);
    }

    public List<Item> getItens() {
        return repository.getItens();
    }

}
