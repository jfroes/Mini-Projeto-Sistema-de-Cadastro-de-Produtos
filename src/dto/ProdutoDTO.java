package dto;

import entities.Produto;

public class ProdutoDTO {
    private  Integer id;
    private String nome;
    private Double preco;
    private Integer quantidade;

    public ProdutoDTO() {
    }

    public ProdutoDTO(Integer id, String nome, Double preco, Integer quantidade) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public ProdutoDTO(Produto entity){
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.preco = entity.getPreco();
        this.quantidade = entity.getQuantidade();
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Double getPreco() {
        return preco;
    }

    public Integer getQuantidade() {
        return quantidade;
    }
}
