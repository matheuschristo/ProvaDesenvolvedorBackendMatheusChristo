package com.project.matheuschristo.repository;

import com.project.matheuschristo.model.Pedido;
import com.project.matheuschristo.service.PedidoService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, UUID> {

    Optional<Pedido> findPedidoById(UUID id);
}
