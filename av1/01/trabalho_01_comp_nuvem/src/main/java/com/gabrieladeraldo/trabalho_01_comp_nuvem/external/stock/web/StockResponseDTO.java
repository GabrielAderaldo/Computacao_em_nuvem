package com.gabrieladeraldo.trabalho_01_comp_nuvem.external.stock.web;

public class StockResponseDTO {
    private Long id;
    private Long produtoId;
    private Integer quantidade;

    public StockResponseDTO() {
    }

    public StockResponseDTO(Long id, Long produtoId, Integer quantidade) {
        this.id = id;
        this.produtoId = produtoId;
        this.quantidade = quantidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
