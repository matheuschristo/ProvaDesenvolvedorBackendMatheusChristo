package com.project.matheuschristo.repository;

import com.project.matheuschristo.model.ProdutoServico;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProdutoServicoRepository extends JpaRepository<ProdutoServico, UUID> {

    Optional<ProdutoServico> findProdutoServicoById(UUID id);

    @Query(value = """
                SELECT * FROM produto_servico pc
                LIMIT :pageSize
                OFFSET :firstResult
            """,nativeQuery = true)
    Optional<List<ProdutoServico>> buscarProdutoServicos(@Param("pageSize") Integer pageSize, @Param("firstResult") Integer firstResult);
}
