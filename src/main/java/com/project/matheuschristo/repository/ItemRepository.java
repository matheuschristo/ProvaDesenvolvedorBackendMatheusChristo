package com.project.matheuschristo.repository;

import com.project.matheuschristo.model.Item;
import com.project.matheuschristo.model.ItemProdutoServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ItemRepository extends JpaRepository<Item, UUID> {

    Optional<Item> findItemById(UUID id);

    @Query(value = """
                SELECT * FROM item i
                WHERE i.produto_servico_id = :id
                AND i.pedido_id IS NOT NULL
                LIMIT 1
            """, nativeQuery = true)
    Optional<Item> buscarItemComPedidoByProdudoServicoId(@Param("id") UUID id);

    @Query(value = """
                SELECT * FROM item i
            """, nativeQuery = true)
    List<Item> getItens();

    @Query(value = """
                SELECT * FROM item i
                WHERE i.pedido_id = :pedidoId
                LIMIT :pageSize
                OFFSET :firstResult
            """, nativeQuery = true)
    Optional<List<Item>> findItemByPedidoId(@Param("pedidoId") UUID pedidoId, @Param("pageSize") Integer pageSize, @Param("firstResult") Integer fistResult);
}
