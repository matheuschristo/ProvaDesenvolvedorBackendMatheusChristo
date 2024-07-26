package com.project.matheuschristo.service;

import com.project.matheuschristo.model.Item;
import com.project.matheuschristo.repository.ItemRepository;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    private ItemRepository repository;

    public ItemService(ItemRepository repository) {
        this.repository = repository;
    }

    public void createItem(Item item) {
        repository.save(item);
    }

}
