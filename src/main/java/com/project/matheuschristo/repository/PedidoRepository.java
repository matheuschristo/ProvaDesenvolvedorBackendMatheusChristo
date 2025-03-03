package com.project.matheuschristo.repository;

import com.project.matheuschristo.model.Pedido;
import com.project.matheuschristo.service.PedidoService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, UUID> {

    Optional<Pedido> findPedidoById(UUID id);

    @Query(value = """
                SELECT * FROM pedido p
          """, nativeQuery = true)
    List<Pedido> getPedidos();

    @Query(value = """
                SELECT * FROM pedido p
                LIMIT :pageSize
                OFFSET :firstResult
            """, nativeQuery = true)
    Optional<Pedido> buscarPedidos(@Param("pageSize") Integer pageSize, @Param("firstResult") Integer fistResult);
}
