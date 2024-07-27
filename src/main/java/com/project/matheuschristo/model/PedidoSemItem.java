package com.project.matheuschristo.model;

import java.math.BigDecimal;

public class PedidoSemItem {

    private BigDecimal total;

    private BigDecimal desconto;

    private boolean isAberto;

    public PedidoSemItem(BigDecimal total, BigDecimal desconto, boolean isAberto) {
        this.total = total;
        this.desconto = desconto;
        this.isAberto = isAberto;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public void setDesconto(BigDecimal desconto) {
        this.desconto = desconto;
    }

    public boolean isAberto() {
        return isAberto;
    }

    public void setAberto(boolean aberto) {
        isAberto = aberto;
    }
}
