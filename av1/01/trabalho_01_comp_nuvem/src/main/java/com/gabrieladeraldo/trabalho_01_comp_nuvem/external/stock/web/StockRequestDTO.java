package com.gabrieladeraldo.trabalho_01_comp_nuvem.external.stock.web;

public class StockRequestDTO {
    private Long produtoId;
    private Integer quantidade;

    public StockRequestDTO() {
    }

    public StockRequestDTO(Long produtoId, Integer quantidade) {
        this.produtoId = produtoId;
        this.quantidade = quantidade;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
