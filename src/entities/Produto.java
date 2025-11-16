package entities;

import java.util.Objects;

public class Produto {
    private Integer id;
    private String nome;
    private Double preco;
    private Integer quantidade;

    public Produto(){
    }

    public Produto(Integer id, String nome, Double preco, Integer quantidade) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", preco=" + preco +
                ", quantidade=" + quantidade +
                '}';
    }

    public void atualizarPreco(double novoPreco){
        this.setPreco(novoPreco);
    }

    public void reporEstoque(int qtd){
        if (qtd <= 0) {
            throw new IllegalArgumentException("Quantidade nao pode ser zero");
        }else if(qtd <= this.quantidade){
            throw new IllegalArgumentException("Quantidade nao pode menor que o estoque atual (" + this.quantidade + ")");
        }else {
            this.quantidade += qtd;
        }
    }

    public void vender(int qtd){
        if (this.quantidade - qtd <=0 ){
            throw new IllegalArgumentException("Estoque insuficente para verder " + qtd);
        }else{
            this.quantidade -= qtd;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Produto produto = (Produto) o;
        return Objects.equals(id, produto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
