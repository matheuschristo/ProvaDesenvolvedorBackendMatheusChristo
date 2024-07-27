package com.project.matheuschristo.repository;

import com.project.matheuschristo.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ItemRepository extends JpaRepository<Item, UUID> {

    Item findItemById(UUID id);

    @Query(value = """
                SELECT DISTINCT(*) FROM item
                WHERE produto_servico = :id
                AND produto_id IS NOT NULL
            """)
    Optional<Item> findItemComPedidoByProdudoServicoId(@Param("id") UUID id);
}
