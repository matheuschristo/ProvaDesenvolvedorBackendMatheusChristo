package com.project.matheuschristo.service;

import com.project.matheuschristo.model.ProdutoServico;
import com.project.matheuschristo.repository.ProdutoServicoRepository;
import org.springframework.stereotype.Service;

@Service
public class ProdutoServicoService {

    private ProdutoServicoRepository repository;

    public ProdutoServicoService(ProdutoServicoRepository repository) {
        this.repository = repository;
    }

    public ProdutoServico create(ProdutoServico produtoServico) {
        repository.save(produtoServico);

        return produtoServico;
    }
}
