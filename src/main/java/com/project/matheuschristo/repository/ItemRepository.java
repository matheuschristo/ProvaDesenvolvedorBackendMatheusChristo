package com.project.matheuschristo.repository;

import com.project.matheuschristo.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ItemRepository extends JpaRepository<Item, UUID> {

    Item findItemById(UUID id);

    @Query(value = """
                SELECT DISTINCT(*) FROM item i
                WHERE i.produto_servico = :id
                AND i.produto_id IS NOT NULL
            """, nativeQuery = true)
    Optional<Item> buscarItemComPedidoByProdudoServicoId(@Param("id") UUID id);

    @Query(value = """
                SELECT * FROM item i
            """, nativeQuery = true)
    List<Item> getItens();
}
