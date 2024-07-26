package com.project.matheuschristo.repository;

import com.project.matheuschristo.model.ProdutoServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProdutoServicoRepository extends JpaRepository<ProdutoServico, UUID> {


}
