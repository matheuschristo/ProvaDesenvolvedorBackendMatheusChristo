package com.project.matheuschristo.service;

import com.project.matheuschristo.model.Item;
import com.project.matheuschristo.model.ProdutoServico;
import com.project.matheuschristo.repository.ItemRepository;
import com.project.matheuschristo.repository.ProdutoServicoRepository;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProdutoServicoService {

    private final ProdutoServicoRepository repository;
    private final ItemRepository itemRepository;

    public ProdutoServicoService(ProdutoServicoRepository repository, ItemRepository itemRepository) {
        this.repository = repository;
        this.itemRepository = itemRepository;
    }

    public ProdutoServico create(ProdutoServico produtoServico) {
        repository.save(produtoServico);
        return produtoServico;
    }

    public void update(UUID id, ProdutoServico produtoServico) throws Exception {
        ProdutoServico newProdutoServico = repository.findProdutoServicoById(id).orElseThrow(() -> new Exception("Produto/Servico não encontrado."));;

        if (produtoServico.getNome() != null) newProdutoServico.setNome(produtoServico.getNome());
        if (produtoServico.getPreco() != null) newProdutoServico.setPreco(produtoServico.getPreco());

        newProdutoServico.setDesativado(produtoServico.isDesativado());
        newProdutoServico.setProduto(produtoServico.isProduto());

        repository.save(newProdutoServico);
    }

    public void delete(UUID id) throws Exception {
        ProdutoServico produtoServico = repository.findProdutoServicoById(id).orElseThrow(() -> new Exception("Produto/Servico não encontrado."));;

        System.out.println("debug - "+ itemRepository.buscarItemComPedidoByProdudoServicoId(id).orElse(null));
        if (itemRepository.buscarItemComPedidoByProdudoServicoId(id).orElse(null) != null)
            throw new Exception("Produto/Servico não pode ser excluido pois existe vinculo a um pedido.");

        repository.delete(produtoServico);
    }

    public List<ProdutoServico> buscarProdutoServico(Integer pageSize, Integer pageIndex) throws Exception {
        return repository.buscarProdutoServicos(pageSize, (pageSize * pageIndex)).orElseThrow(() -> new Exception("Erro na busca de produtos/serviços."));
    }
}
