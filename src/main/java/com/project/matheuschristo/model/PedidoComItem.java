package com.project.matheuschristo.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PedidoComItem {

    private BigDecimal desconto;

    private boolean isAberto;

    private List<UUID> itensId = new ArrayList<>();

    public PedidoComItem(BigDecimal desconto, boolean isAberto) {
        this.desconto = desconto;
        this.isAberto = isAberto;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }
    public boolean isAberto() {
        return isAberto;
    }
    public List<UUID> getItensId() {
        return itensId;
    }
}
