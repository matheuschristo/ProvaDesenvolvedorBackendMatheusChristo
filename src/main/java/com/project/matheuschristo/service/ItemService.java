package com.project.matheuschristo.service;

import com.project.matheuschristo.model.Item;
import com.project.matheuschristo.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ItemService {

    private ItemRepository repository;

    public ItemService(ItemRepository repository) {
        this.repository = repository;
    }

    public Item createItem(Item item) {
        repository.save(item);

        return item;
    }

    public void update(UUID id, Item item) throws Exception {
        try {
            Item newItem = repository.findItemById(id);
            newItem.setQuantidade(item.getQuantidade());
            newItem.setPedido(item.getPedido());
            newItem.setProdutoServico(item.getProdutoServico());
        } catch (Exception ex) {
            throw new Exception("Não foi possivel atualizar item.");
        }
    }

    public void delete(UUID id) throws Exception {
        try {
            Item item = repository.findItemById(id);
            repository.delete(item);
        } catch (Exception ex) {
            throw new Exception("Não foi possivel atualizar item.");
        }
    }

    public List<Item> getItens() {
        return repository.getItens();
    }

}
